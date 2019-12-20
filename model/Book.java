package model;

import javax.servlet.http.Part;

public class Book {

public Book() {
		
	}
	
	public int id;
	public int product_id;
	public String author;
	public String title;
	public String publisher;
	public String version;
	public String publish_year;
	public float price;
	public String description;
	public Part book_image;
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
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getPublish_year() {
		return publish_year;
	}
	
	public void setPublish_year(String publish_year) {
		this.publish_year = publish_year;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Part getBook_image() {
		return book_image;
	}
	
	public void setBook_image(Part book_image) {
		this.book_image = book_image;
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
