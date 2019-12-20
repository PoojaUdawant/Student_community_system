package model;

import java.sql.Date;

public class admin_model {
	public admin_model(String username, String firstname, String lastname, String mob_no2, String email, Date date_joined) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mob_no = mob_no2;
		this.email = email;
		this.date_joined = date_joined;
	}
	public admin_model() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String username;
	private String firstname;
	private String lastname;
	private String mob_no;
	private String email;
	private Date date_joined;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMob_no() {
		return mob_no;
	}
	public void setMob_no(String i) {
		this.mob_no = i;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDate_joined() {
		return date_joined;
	}
	public void setDate_joined(Date date_joined) {
		this.date_joined = date_joined;
	}
}
