package model;

import javax.servlet.http.Part;

public class Stationary {

public Stationary() {
		
	}	
		
	public int id;
	public int product_id;
	public String s_name;
	public String s_description;
	public float price;
	public Part s_image;
	public String seller;
	public String posted_on;
	public int status;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getProduct_id() {
		return product_id;
	}
	
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	public String getS_name() {
		return s_name;
	}
	
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	
	public String getS_description() {
		return s_description;
	}
	
	public void setS_description(String s_description) {
		this.s_description = s_description;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public Part getS_image() {
		return s_image;
	}
	
	public void setS_image(Part s_image) {
		this.s_image = s_image;
	}
	
	
	public String getSeller() {
		return seller;
	}
	
	public void setSeller(String seller) {
		this.seller = seller;
	}
	
	public String getPosted_on() {
		return posted_on;
	}
	
	public void setPosted_on(String posted_on) {
		this.posted_on = posted_on;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

}
