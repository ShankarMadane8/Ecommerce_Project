package com.Ecommerce.Entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderHd {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
 	@Id
	int id;
	LocalDate date;
	int customerId;
	double tax;
	double shippingCharges;
	double amount;
	int deliverId;
	int status;
	LocalDate statusDate;
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
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getShippingCharges() {
		return shippingCharges;
	}
	public void setShippingCharges(double shippingCharges) {
		this.shippingCharges = shippingCharges;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getDeliverId() {
		return deliverId;
	}
	public void setDeliverId(int deliverId) {
		this.deliverId = deliverId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public LocalDate getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(LocalDate statusDate) {
		this.statusDate = statusDate;
	}
	
	
}


//CREATE TABLE `orderhd` (
//		  `id` int NOT NULL AUTO_INCREMENT,
//		  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
//		  `customer_id` int DEFAULT NULL,
//		  `tax` double DEFAULT NULL,
//		  `shipping_charges` double DEFAULT NULL,
//		  `amount` double DEFAULT NULL,
//		  `delivery_id` int DEFAULT NULL,
//		  `status` smallint DEFAULT NULL,
//		  `status_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
//		  PRIMARY KEY (`id`)
//		) ;