package model;

public class Cart {

	public Cart() {
		
	}
	
	public int cart_id;
	public String username;
	public float tot_amt;
	
	public int getCart_id() {
		return cart_id;
	}
	
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public float getTot_amt() {
		return tot_amt;
	}
	
	public void setTot_amt(float tot_amt) {
		this.tot_amt = tot_amt;
	}
	

}
