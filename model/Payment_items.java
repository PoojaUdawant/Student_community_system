package model;

public class Payment_items {
	
public Payment_items() {
		
	}
	
	public int payment_id;
	public int product_id;
	public String category;
	public int quantity;
	public float amount;
	
	public int getPayment_id() {
		return payment_id;
	}
	
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	
	public int getProduct_id() {
		return product_id;
	}
	
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public float getAmount() {
		return amount;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	

}
