package com.interview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SlotDAO {

	public boolean addSlot(Slot s) {
	    String sql = "INSERT INTO slots (interviewer_id, slot_date, time_slot, is_booked) VALUES (?, ?, ?, ?)";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement pst = con.prepareStatement(sql)) {

	        pst.setInt(1, s.getInterviewerId());
	        pst.setDate(2, new java.sql.Date(s.getDate().getTime()));
	        pst.setString(3, s.getTimeSlot());
	        pst.setBoolean(4, s.isBooked());

	        return pst.executeUpdate() > 0;

	    } catch (java.sql.SQLIntegrityConstraintViolationException e) {
	        System.out.println("Error: Interviewer ID " + s.getInterviewerId() + " does not exist. Please add the interviewer first.");
	        return false;

	    } catch (Exception e) {
	        System.out.println("Something went wrong. Please check your input.");
	        return false;
	    }
	}

	
	public Slot getEarliestAvailableSlotForInterviewer(int interviewerId) {
	    String sql = "SELECT * FROM slots WHERE interviewer_id = ? AND is_booked = FALSE ORDER BY slot_date ASC, time_slot ASC LIMIT 1";
	    try(Connection con = DBConnection.getConnection();
	        PreparedStatement pst = con.prepareStatement(sql)) {
	        
	        pst.setInt(1, interviewerId); 
	        try(ResultSet rs = pst.executeQuery()) {
	            if(rs.next()) {
	                return new Slot(
	                    rs.getInt("id"),
	                    rs.getInt("interviewer_id"),
	                    rs.getDate("slot_date"),
	                    rs.getString("time_slot"),
	                    rs.getBoolean("is_booked")
	                );
	            }
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	
	public Slot getEarliestAvailableSlotForInterview(Connection con, int interviewerId) throws SQLException {
		String sql="SELECT * FROM slots WHERE interviewer_id = ? AND is_booked = FALSE ORDER BY slot_date, time_slot LIMIT 1";
		try(PreparedStatement pst=con.prepareStatement(sql)){
			pst.setInt(1, interviewerId);
			try(ResultSet rs=pst.executeQuery()) {
				if(rs.next()) {
					return new Slot (
							rs.getInt("id"),
							rs.getInt("interviewer_id"),
							rs.getDate("slot_date"),
							rs.getString("time_slot"),
							rs.getBoolean("is_booked")
							);
				}
			}
			
		}return null;
	}
	public boolean setSlotBooked(Connection con,int slotId,boolean bookeed) throws SQLException{
		String sql = "UPDATE slots SET is_booked = ? Where id= ?";
		try(PreparedStatement ps=con.prepareStatement(sql)) {
			ps.setBoolean(1, bookeed);
			ps.setInt(2,slotId);
			return ps.executeUpdate() > 0;
		}
	}
}
