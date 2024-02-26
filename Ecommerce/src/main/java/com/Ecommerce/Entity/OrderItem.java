package com.Ecommerce.Entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderItem {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
 	@Id
	int id;
	LocalDate date;
	int customerId;
	int orderhdId;
	int itemId;
	int quantity;
	double price;
	double shippingCharges;
	double tax;
	double amount;
	int status;
	Date statusDate;
	String cartType;
	
	
	public String getCartType() {
		return cartType;
	}
	public void setCartType(String cartType) {
		this.cartType = cartType;
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
	public int getOrderhdId() {
		return orderhdId;
	}
	public void setOrderhdId(int orderhdId) {
		this.orderhdId = orderhdId;
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
	public double getShippingCharges() {
		return shippingCharges;
	}
	public void setShippingCharges(double shippingCharges) {
		this.shippingCharges = shippingCharges;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	
	
	
}


/*
 * CREATE TABLE `orderitem` ( `id` int NOT NULL AUTO_INCREMENT, `date` timestamp
 * NULL DEFAULT CURRENT_TIMESTAMP, `customer_id` int DEFAULT NULL, `orderhd_id`
 * int DEFAULT NULL, `item_id` int DEFAULT NULL, `quantity` smallint DEFAULT
 * NULL, `price` double DEFAULT NULL, `shipping_charges` double DEFAULT NULL,
 * `tax` double DEFAULT NULL, `amount` double DEFAULT NULL, `status` smallint
 * DEFAULT NULL, `status_date` timestamp NULL DEFAULT NULL, PRIMARY KEY (`id`) )
 * ;
 */