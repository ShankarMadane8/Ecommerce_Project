package com.Ecommerce.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
 	@Id
	int id;
	Date date;
	int status;
	int customerId;
	int orderhdId;
	int orderItemId;
	int paymentId;
	int deliveryId;
	int refundId;
	double amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public int getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}
	public int getRefundId() {
		return refundId;
	}
	public void setRefundId(int refundId) {
		this.refundId = refundId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}


/*
 * CREATE TABLE `transaction` ( `id` int NOT NULL AUTO_INCREMENT, `date`
 * timestamp NULL DEFAULT CURRENT_TIMESTAMP, `status` smallint DEFAULT NULL,
 * `customer_id` int DEFAULT NULL, `orderhd_id` int DEFAULT NULL, `orderitem_id`
 * int DEFAULT NULL, `payment_id` int DEFAULT NULL, `delivery_id` int DEFAULT
 * NULL, `return_id` int DEFAULT NULL, `refund_id` int DEFAULT NULL, `amount`
 * double DEFAULT NULL, PRIMARY KEY (`id`) ) ;
 */