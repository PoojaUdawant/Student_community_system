package model;

public class Payment {
	
public Payment() {
		
	}
	
	public int payment_id;
	public int cart_id;
	public String seller;
	public float payment_amount;
	public String payment_type;
	public String payment_date;
	
	public int getPayment_id() {
		return payment_id;
	}
	
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	
	public int getCart_id() {
		return cart_id;
	}
	
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	
	public String getSeller() {
		return seller;
	}
	
	public void setSeller(String seller) {
		this.seller = seller;
	}
	
	public float getPayment_amount() {
		return payment_amount;
	}
	
	public void setPayment_amount(float payment_amount) {
		this.payment_amount = payment_amount;
	}
	
	public String getPayment_type() {
		return payment_type;
	}
	
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	
	public String getPayment_date() {
		return payment_date;
	}
	
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	

}
