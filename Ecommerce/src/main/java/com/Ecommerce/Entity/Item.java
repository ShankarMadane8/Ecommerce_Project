package com.Ecommerce.Entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;

@Entity
public class Item {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	int id;
	String brand;
	int category;
	
	@Column(length = 1500) 
	String description;
	float discount;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	byte[] image;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	byte[] image1;
	
	public byte[] getImage1() {
		return image1;
	}
	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}
	
	@Column(length = 500)
	String name;
	float price;
	float rating;
	String review;
	int status;
	
	@Transient
	String imagePath;
	
	@Transient
	String imagePath1;
	
	@Transient
	int total_quantity;
	
	@Transient
	String heart;
	
	String html;
	
	
	public String getHeart() {
		return heart;
	}
	public void setHeart(String heart) {
		this.heart = heart;
	}
	public String getImagePath1() {
		return imagePath1;
	}
	public void setImagePath1(String imagePath1) {
		this.imagePath1 = imagePath1;
	}
	public int getTotal_quantity() {
		return total_quantity;
	}
	public void setTotal_quantity(int total_quantity) {
		this.total_quantity = total_quantity;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
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
	
	public String getHtml() {
		return html;
	}
	
	public void setHtml(String html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", brand=" + brand + ", category=" + category + ", description=" + description
				+ ", discount=" + discount + ", name=" + name + ", price=" + price + ", rating=" + rating + ", review="
				+ review + ", status=" + status + ", total_quantity=" + total_quantity + ", heart=" + heart + "]";
	}
	
	
	
}



/*
CREATE TABLE `item` (
		  `id` int NOT NULL,
		  `brand` varchar(255) DEFAULT NULL,
		  `category` int NOT NULL,
		  `description` varchar(255) DEFAULT NULL,
		  `discount` double NOT NULL,
		  `image` mediumblob,
		  `name` varchar(255) DEFAULT NULL,
		  `price` float NOT NULL,
		  `rating` int NOT NULL,
		  `review` varchar(255) DEFAULT NULL,
		  `status` int NOT NULL,
		  PRIMARY KEY (`id`)
		);*/