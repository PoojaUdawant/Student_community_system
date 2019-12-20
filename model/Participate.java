package model;

public class Participate {

public Participate() {
		
	}

	public String student;
	public int workshop_id;
	public String date_of_registeration;
	
	public String getStudent() {
		return student;
	}
	
	public void setStudent(String student) {
		this.student = student;
	}
	
	public int getWorkshop_id() {
		return workshop_id;
	}
	
	public void setWorkshop_id(int workshop_id) {
		this.workshop_id = workshop_id;
	}
	
	public String getDate_of_registeration() {
		return date_of_registeration;
	}
	
	public void setDate_of_registeration(String date_of_registeration) {
		this.date_of_registeration = date_of_registeration;
	}
}
