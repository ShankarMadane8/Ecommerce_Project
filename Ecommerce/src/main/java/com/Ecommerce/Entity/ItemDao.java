package com.Ecommerce.Entity;

import org.springframework.web.multipart.MultipartFile;

public class ItemDao {
	

	String brand;
	int category;
	String description;
	float discount;
	
	public MultipartFile image;
	public MultipartFile image1;
	public MultipartFile getImage1() {
		return image1;
	}
	public void setImage1(MultipartFile image1) {
		this.image1 = image1;
	}
	String name;
	float price;
	float rating;
	String review;
	int status;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ItemDao [brand=" + brand + ", category=" + category + ", description=" + description + ", discount="
				+ discount + ", name=" + name + ", price=" + price + ", rating=" + rating + ", review=" + review
				+ ", status=" + status + "]";
	}
	
	
}
