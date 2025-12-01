package com.interview;

public class Interviewer {

	private int id;
	private String name;
	private String email;
	private String specialization;
	
	public Interviewer() {}
	
	public Interviewer(String name,String email,String specialization) {
		this.name=name;
		this.email=email;
		this.specialization=specialization;
	}
	
	public Interviewer(int id,String name,String email,String specialization) {
		this.id=id;
		this.name=name;
		this.email=email;
		this.specialization=specialization;
	}
	
	public int getId()
	{
		return id;
	}
	public void setInt(int id)
	{
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
	
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization=specialization;
	}
}
