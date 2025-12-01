package com.interview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InterviewerDAO {

	public boolean addInterviewer(Interviewer i) {
		String sql="INSERT INTO interviewers (name, email, specialization) VALUES(?, ?, ?)";
		try(
				Connection con=DBConnection.getConnection();
				PreparedStatement pst=con.prepareStatement(sql)) 
		{
			pst.setString(1, i.getName());
			pst.setString(2, i.getEmail());
			pst.setString(3, i.getSpecialization());
			return pst.executeUpdate() > 0;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public Interviewer getInterviewerById(int id) {
		String sql="SELECT * FROM interviewers";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement pst=con.prepareStatement(sql)) {
			pst.setInt(1, id);
			try(ResultSet rs=pst.executeQuery()) {
				if(rs.next()) {
					return new Interviewer(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("email"),
							rs.getString("specialization")
							);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Interviewer> getInterviewersBySkills(List<String> skillTokens) {
		List<Interviewer> list=new ArrayList<>();
		String sql="SELECT * FROM interviewers";
		try(Connection con=DBConnection.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);
				ResultSet rs=pst.executeQuery()) {
			while(rs.next()) {
				String spec = rs.getString("specialization");
				if(spec == null) continue;
				String specLower = spec.toLowerCase();
				boolean matched = false;
				for(String token : skillTokens ) {
					if(specLower.contains(token)) {
						matched = true;
						break;
					}
				}
				if(matched) {
					list.add(new Interviewer (
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("email"),
							rs.getString("specialization")
							));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Interviewer> getAllInterviewers() {
		List<Interviewer> list = new ArrayList<>();
		String sql="SELECT * FROM interviewers";
		try(Connection con=DBConnection.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);
				ResultSet rs=pst.executeQuery()) {
			while(rs.next()) {
				list.add(new Interviewer(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("specialization")
						));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
