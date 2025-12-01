package com.interview;

public class Candidate {

	private int id;
	private String name;
	private String email;
	private String skills;
	
	public Candidate() {}
	
	public Candidate(String name,String email,String skills)
	{
		this.name=name;
		this.email=email;
		this.skills=skills;
	}
	
	public Candidate(int id,String name,String email,String skills) {
		this.id=id;
		this.name=name;
		this.email=email;
		this.skills=skills;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public String getSkills() {
		return skills;
	}
	
	public void setSkills(String skills) {
		this.skills=skills;
	}
	
}
