package model;

import javax.servlet.http.Part;

public class User {
	public User() {
		
	}
	
	public String username;
	public String first_name;
	public String last_name;
	public long mob_no;
	public String email;
	public Part image;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public long getMob_no() {
		return mob_no;
	}
	
	public void setMob_no(long mob_no) {
		this.mob_no = mob_no;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Part getImage() {
		return image;
	}
	
	public void setImage(Part image) {
		this.image = image;
	}
}
