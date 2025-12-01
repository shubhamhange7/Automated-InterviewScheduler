package com.interview;

public class Schedule {

	private int id;
	private int candidateId;
	private int interviewerId;
	private int slotId;
	private String status;
	
	public Schedule() {}
	
	public Schedule(int candidateId,int interviewerId,int slotId,String status) {
	this.candidateId=candidateId;
	this.interviewerId=interviewerId;
	this.slotId=slotId;
	this.status=status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	
	public int getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(int candidateId) {
		this.candidateId=candidateId;
	}
	
	public int getInterviewerId() {
		return interviewerId;
	}
	public void setInterviewerId(int interviewerId) {
		this.interviewerId=interviewerId;
	}
	
	public int getSlotId() {
		return slotId;
	}
	public void setSlotId(int slotId) {
		this.slotId=slotId;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status=status;
	}
}
