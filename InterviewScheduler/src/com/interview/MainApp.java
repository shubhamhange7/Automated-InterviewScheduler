package com.interview;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MainApp {
	private static CandidateDAO candidateDAO = new CandidateDAO();
	private static InterviewerDAO interviewerDAO = new InterviewerDAO();
	private static SlotDAO slotDAO = new SlotDAO();
	private static ScheduleDAO scheduleDAO = new ScheduleDAO();
	
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		while(true) {
			printMenu();
			String ch=sc.nextLine().trim();
			switch(ch) {
			case "1": addCandidate(sc);break;
			case "2": addInterviewer(sc);break;
			case "3": addSlot(sc); break;
			case "4": scheduleInterview(sc); break;
			case "5": viewSchedules(); break;
			case "6": viewAllCandidates(); break;
			case "7": viewAllInterviewers(); break;
			case "8": System.out.println("Exiting...."); sc.close(); System.exit(0);
			default: System.out.println("Invalid choice");
			
			}
		}
	}
	
	private static void printMenu() {
		System.out.println("\n=== Automated Interview Scheduling ===");
		System.out.println("1.Add Candidate");
		System.out.println("2.Add Interviewer");
		System.out.println("3.Add Slot(Interviewer availabilty)");
		System.out.println("4.Schedule Interview(auto_asign)");
		System.out.println("5.View all scheduled interviews");
		System.out.println("6.View all candidates");
		System.out.println("7.View all interviewers");
		System.out.println("8.Exit");
		System.out.print("Choice: ");
	}
	
	private static void addCandidate(Scanner sc) {
		System.out.print("Name: "); String name=sc.nextLine();
		System.out.print("Email:"); String email=sc.nextLine();
		System.out.print("Skills: (Comma-saperated, eg. java,spring,jdbc): "); String skills=sc.nextLine();
		Candidate c=new Candidate(name, email, skills);
		if(candidateDAO.addCandidate(c)) 
			System.out.println("Candidate added");
		else
			System.out.println("Failed to add candidate");
	}
	
	private static void addInterviewer(Scanner sc) {
		System.out.print("Name: "); 
		String name=sc.nextLine();
		System.out.print("Email: ");
		String email=sc.nextLine();
		System.out.println("Specialization:(Use Comma, eg.java,spring): ");
		String spec=sc.nextLine();
		Interviewer i= new Interviewer(name, email, spec);
		if(interviewerDAO.addInterviewer(i))
			System.out.println("Interviewer Added");
		else
			System.out.println("Failed To Add Interviewer");
	}
	
	private static void addSlot(Scanner sc) {
		try {
			System.out.print("Interviewer ID: ");
			int interviewerId=sc.nextInt();
			sc.nextLine();
			System.out.print("Date (YYYY-MM-DD): ");
			String dateStr=sc.nextLine();
			System.out.print("Time slot (eg. 10:00-11:00): ");
			String timeSlot=sc.nextLine();
			Date date=Date.valueOf(dateStr);
			Slot s=new Slot(interviewerId, date, timeSlot);
			if(slotDAO.addSlot(s))
				System.out.println("Slot Added");
			else
				System.out.println("Failed to add slot");
		}catch(Exception e) {
			System.out.println("Invalid input. Please follow formats");
		}
	}
	
	private static void scheduleInterview(Scanner sc) {
		try {
			System.out.println("Enter Candidate Id to Schedule: ");
			int candId =Integer.parseInt(sc.nextLine());
			boolean ok = scheduleDAO.scheduleInterview(candId);
			if(ok)
				System.out.println("Scheduling Successful");
			else
				System.out.println("Scheduling Failed");
			
		}catch(Exception e) {
			System.out.println("Invalid input");
		}
	}
	
	private static void viewSchedules() {
		List<String> lines= scheduleDAO.viewAllSchedules();
		if(lines.isEmpty())
			System.out.println("No schedules found");
		else
			System.out.println("\n--- All Schedules ---");
			for(String s: lines)
				System.out.println(s);
	}
	
	private static void viewAllCandidates() {
	    List<Candidate> list = candidateDAO.getAllCandidates();
	    if (list.isEmpty()) {
	        System.out.println("No candidates found.");
	    } else {
	        System.out.println("\n--- All Candidates ---");
	        for (Candidate c : list) {
	            System.out.println("ID: " + c.getId());
	            System.out.println("Name: " + c.getName());
	            System.out.println("Email: " + c.getEmail());
	            System.out.println("Skills: " + c.getSkills());
	        }
	    }
	}
	
	private static void viewAllInterviewers() {
		List<Interviewer> list= interviewerDAO.getAllInterviewers();
		if(list.isEmpty()) {
			System.out.println("No interviewers found.");
		}else {
			System.out.println("\n--- All Interviewers ---");
			for(Interviewer i : list) {
				System.out.println("ID: "+i.getId());
				System.out.println("Name: "+i.getName());
				System.out.println("Email: "+i.getEmail());
				System.out.println("Specialization: "+i.getSpecialization());
			}
		}
	}

}