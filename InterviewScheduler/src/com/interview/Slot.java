package com.interview;

import java.sql.Date;

public class Slot {

	private int id;
	private int interviewerId;
	private Date date;
	private String timeSlot;
	private boolean isBooked;

	public Slot() {}
	
	public Slot(int interviewerId,Date date,String timeSlot) {
		this.interviewerId=interviewerId;
		this.date=date;
		this.timeSlot=timeSlot;
		this.isBooked=false;
	}
	
	public Slot(int id,int interviewerId,Date date,String timeSlot,boolean isBooked) {
		this.id=id;
		this.interviewerId=interviewerId;
		this.date=date;
		this.timeSlot=timeSlot;
		this.isBooked=isBooked;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	
	public int getInterviewerId() {
		return interviewerId;
	}
	public void setInterviewerId(int interviewerId) {
		this.interviewerId=interviewerId;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date=date;
	}
	
	public String getTimeSlot() {
		return timeSlot;
	}
	
	public void setTimeSlot(String timeSlot) {
		this.timeSlot=timeSlot;
	}
	
	public boolean isBooked() {
		return isBooked;
	}
	public void setBooked(boolean booked) {
		isBooked=booked;
	}
}