package model;

import javax.servlet.http.Part;

public class Workshop {

public Workshop(int workshop_id, String title, String conducted_by, String w_start_date) {
		super();
		this.workshop_id = workshop_id;
		this.title = title;
		this.conducted_by = conducted_by;
		this.w_start_date = w_start_date;
	}

public Workshop() {
	}
	

	public int workshop_id;
	public String title;
	public String conducted_by;
	public String w_start_date;
	public String w_end_date;
	public int duration;
	public int tot_seats;
	public int vacant_seats;
	public float fees;
	public String posted_by;
	public String posted_on;
	public int status;
	public Part pdf;
	
	
	public int getWorkshop_id() {
		return workshop_id;
	}
	
	public void setWorkshop_id(int workshop_id) {
		this.workshop_id = workshop_id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getConducted_by() {
		return conducted_by;
	}
	
	public void setConducted_by(String conducted_by) {
		this.conducted_by = conducted_by;
	}
	
	public String getW_start_date() {
		return w_start_date;
	}
	
	public void setW_start_date(String w_start_date) {
		this.w_start_date = w_start_date;
	}
	
	public String getW_end_date() {
		return w_end_date;
	}
	
	public void setW_end_date(String w_end_date) {
		this.w_end_date = w_end_date;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getTot_seats() {
		return tot_seats;
	}
	
	public void setTot_seats(int tot_seats) {
		this.tot_seats = tot_seats;
	}
	
	public int getVacant_seats() {
		return vacant_seats;
	}
	
	public void setVacant_seats(int vacant_seats) {
		this.vacant_seats = vacant_seats;
	}
	
	public float getFees() {
		return fees;
	}
	
	public void setFees(float fees) {
		this.fees = fees;
	}
	
	
	public String getPosted_on() {
		return posted_on;
	}
	
	public void setPosted_on(String posted_on) {
		this.posted_on = posted_on;
	}
	
	public String getPosted_by() {
		return posted_by;
	}
	
	public void setPosted_by(String posted_by) {
		this.posted_by = posted_by;
	}
	
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Part getPdf() {
		return pdf;
	}
	
	public void setPdf(Part pdf) {
		this.pdf = pdf;
	}
	
	
}
