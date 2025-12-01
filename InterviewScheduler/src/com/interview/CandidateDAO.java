package com.interview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO {

	public boolean addCandidate(Candidate c) {
		String sql="INSERT INTO candidates(name, email, skills) VALUES (?, ?, ?)";
		try (
			Connection con=DBConnection.getConnection();
			PreparedStatement pst=con.prepareStatement(sql)) {
			pst.setString(1,c.getName());
			pst.setString(2,c.getEmail());
			pst.setString(3,c.getSkills());
			return pst.executeUpdate() > 0;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Candidate getCandidateById(int id) {
		String sql="SELECT * FROM candidates WHERE id=?";
		try 
			(Connection con=DBConnection.getConnection();
			PreparedStatement pst=con.prepareStatement(sql)) {
			pst.setInt(1, id);
			try(ResultSet rs=pst.executeQuery()){
				if(rs.next()) {
					return new Candidate(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("skills")
					);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Candidate> getAllCandidates(){
		List<Candidate> list=new ArrayList<>();
		String sql="SELECT * FROM candidates";
		try(Connection con=DBConnection.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);
				ResultSet rs=pst.executeQuery()) {
		while (rs.next()) {
			list.add(new Candidate(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("skills")
					));
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
