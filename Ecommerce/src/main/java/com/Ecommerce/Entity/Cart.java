package com.Ecommerce.Entity;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Cart {
	@GeneratedValue(strategy = GenerationType.AUTO)
 	@Id
	int id;
	LocalDate date;
	int customerId;
	int productId;
	int itemId;
	int quantity;
	double price;
	int status;
	String type;
	
    
	
		
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}


}


/*
 * CREATE TABLE `cart` ( `id` int NOT NULL AUTO_INCREMENT, `date` timestamp NULL
 * DEFAULT CURRENT_TIMESTAMP, `customer_id` int DEFAULT NULL, `product_id` int
 * DEFAULT NULL, `item_id` int DEFAULT NULL, `quantity` smallint DEFAULT NULL,
 * `price` double DEFAULT NULL, `status` smallint DEFAULT NULL, PRIMARY KEY
 * (`id`) ) ;
 */