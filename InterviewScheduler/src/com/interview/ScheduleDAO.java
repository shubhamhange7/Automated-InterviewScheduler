package com.interview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {

	private InterviewerDAO interviewerDAO = new InterviewerDAO();
	private SlotDAO slotDAO=new SlotDAO();
	private CandidateDAO candidateDAO=new CandidateDAO();
	
	
	public boolean scheduleInterview(int candidateId) {
		Candidate candidate = candidateDAO.getCandidateById(candidateId);
		if(candidate == null) {
			System.out.println("Candidate not found with id: "+candidateId);
			return false;
		}
		
		List<String> tokens = parseSkillTokens(candidate.getSkills());
		if(tokens.isEmpty()) {
			System.out.println("Candidate not found with id: "+candidateId);
			return false;
		}
		
		List<Interviewer> matched = interviewerDAO.getInterviewersBySkills(tokens);
		if(matched.isEmpty()) {
			System.out.println("No interviewers match candidate's skills.");
			return false;
		}
		try(Connection con=DBConnection.getConnection()) {
			con.setAutoCommit(false);
			try {
				for(Interviewer interviewer : matched) {
					Slot freeSlot = slotDAO.getEarliestAvailableSlotForInterview(con, interviewer.getId());
					if(freeSlot != null) {
						boolean updated = slotDAO.setSlotBooked(con, freeSlot.getId(), true);
						if(!updated) continue;
						
						String sql = "INSERT INTO schedules (candidate_id, interviewer_id, slot_id, status) VALUES(?, ?, ?, ?)";
						try(PreparedStatement pst=con.prepareStatement(sql)) {
							pst.setInt(1, candidateId);
							pst.setInt(2, interviewer.getId());
							pst.setInt(3,freeSlot.getId());
							pst.setString(4, "Scheduled");
							int r=pst.executeUpdate();
							if(r>0) {
								con.commit();
								System.out.println("Scheduled with interviewer: "+ interviewer.getName()+" on "+ freeSlot.getDate()+" at "+freeSlot.getTimeSlot());
							return true;
							}else {
							con.rollback();
						}
					}
				}
			}
				con.rollback();
				System.out.println("No available slots found for matched interviewers.");
				return false;
			}catch(Exception e) {
				con.rollback();
				e.printStackTrace();
				return false;
			}finally {
				con.setAutoCommit(true);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	private List<String> parseSkillTokens(String skillsCsv) {
		 List<String> tokens = new ArrayList<>();
		 if(skillsCsv == null) return tokens;
		 String[] parts = skillsCsv.split(",");
		 for(String p : parts) {
			 String t = p.trim().toLowerCase();
			 if(!t.isEmpty()) tokens.add(t);
		 }return tokens;
	}
	
	public List<String> viewAllSchedules() {
		List<String> out= new ArrayList<>();
		
		String sql="SELECT s.id as sched_id, c.id as cand_id, c.name as cand_name, i.id as int_id, i.name as int_name,"
				+"sl.slot_date as slot_date, sl.time_slot as time_slot,s.status as status "
				+"FROM schedules s "
				+"JOIN candidates c ON s.candidate_id = c.id "
				+"JOIN interviewers i ON s.interviewer_id = i.id "
				+"JOIN slots sl ON s.slot_id = sl.id "
				+"ORDER BY sl.slot_date, sl.time_slot ";
		try(Connection con= DBConnection.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);
				ResultSet rs=pst.executeQuery()) {
			while(rs.next()) {
				String line = String.format("Scheduled_Id:%d | Candidate(%d:%s) | Interviewer(%d:%s | Date:%s | Time:%s | Status:%s",
						rs.getInt("sched_id"),
						rs.getInt("cand_id"),
						rs.getString("cand_name"),
						rs.getInt("int_id"),
						rs.getString("int_name"),
						rs.getDate("slot_date").toString(),
						rs.getString("time_slot"),
						rs.getString("status")
						);
				out.add(line);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return out;
	}
}
