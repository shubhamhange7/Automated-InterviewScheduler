Automated Interview Scheduler (Java + JDBC)

A Java console-based application that automates the process of assigning interview slots to candidates.
It allows adding candidates, interviewers, available slots, and automatically schedules interviews based on availability.

📌 Features
✅ Candidate Management

Add new candidates with name, email, and skills

Fetch all candidates from the database

✅ Interviewer Management

Add interviewers with specialization details

Fetch all interviewers

✅ Slot Management

Add available time slots for interviewers

Each slot contains: interviewer ID, date, and time range

✅ Automatic Interview Scheduling

Automatically assigns the earliest available slot

Ensures each slot is booked only once

Stores scheduled interviews in the database

✅ View Scheduled Interviews

Displays:

Candidate Name

Interviewer Name

Date & Time Slot

📁 Project Structure
InterviewScheduler/
 ├── src/com/interview/
 │      ├── MainApp.java
 │      ├── Candidate.java
 │      ├── Interviewer.java
 │      ├── Slot.java
 │      ├── CandidateDAO.java
 │      ├── InterviewerDAO.java
 │      ├── SlotDAO.java
 │      ├── ScheduleDAO.java
 │      ├── DBConnection.java
 └── README.md

🗄️ Database Setup (MySQL)

Create the database:

CREATE DATABASE interview_scheduler;
USE interview_scheduler;

Table: candidates
CREATE TABLE candidates (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100),
  skills VARCHAR(255)
);

Table: interviewers
CREATE TABLE interviewers (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100),
  specialization VARCHAR(255)
);

Table: slots
CREATE TABLE slots (
  id INT AUTO_INCREMENT PRIMARY KEY,
  interviewerId INT,
  date DATE,
  timeSlot VARCHAR(50),
  isBooked BOOLEAN DEFAULT FALSE
);

Table: schedules
CREATE TABLE schedules (
  id INT AUTO_INCREMENT PRIMARY KEY,
  candidateId INT,
  interviewerId INT,
  slotId INT
);

⚙️ How to Run the Project
1. Import Project into Eclipse

File → Import → Existing Projects into Workspace

Select the project folder

2. Add MySQL Connector JAR

Right-click project → Build Path → Add External Archives

Choose mysql-connector-j.jar

3. Configure DBConnection.java
String url = "jdbc:mysql://localhost:3306/interview_scheduler";
String user = "root";
String pass = "your_password";

4. Run MainApp.java

You will see this menu:

1. Add Candidate
2. Add Interviewer
3. Add Slot
4. Schedule Interview (Auto)
5. View All Scheduled Interviews
6. Exit

📝 Future Enhancements

Cancel or reschedule interviews

Admin login

Email notifications

GUI or Web UI

👤 Author

Shubham Hange
GitHub: shubhamhange7
