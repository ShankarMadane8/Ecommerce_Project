 package com.Ecommerce.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Delivery {
	@GeneratedValue(strategy = GenerationType.AUTO)
 	@Id
	int id;
	int customerId;
	int orderhdId;
	int orderItemId;
	int addressId;
	String phoneno;
	Date expectedDate;
	Date actualDate;
	String actualTime;
	int courierId;
	String trackingId;
	String deliverBy;
	int status;
	Date statusDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public Date getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}
	public Date getActualDate() {
		return actualDate;
	}
	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}
	public String getActualTime() {
		return actualTime;
	}
	public void setActualTime(String actualTime) {
		this.actualTime = actualTime;
	}
	public int getCourierId() {
		return courierId;
	}
	public void setCourierId(int courierId) {
		this.courierId = courierId;
	}
	public String getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	public String getDeliverBy() {
		return deliverBy;
	}
	public void setDeliverBy(String deliverBy) {
		this.deliverBy = deliverBy;
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
 * CREATE TABLE `delivery` ( `id` int NOT NULL AUTO_INCREMENT, `customer_id` int
 * DEFAULT NULL, `orderhd_id` int DEFAULT NULL, `orderitem_id` int DEFAULT NULL,
 * `address` varchar(200) DEFAULT NULL, `phoneno` varchar(30) DEFAULT NULL,
 * `expected_date` date DEFAULT NULL, `actual_date` date DEFAULT NULL,
 * `actual_time` time DEFAULT NULL, `courier_id` int DEFAULT NULL, `tracking_id`
 * varchar(50) DEFAULT NULL, `deliver_by` varchar(50) DEFAULT NULL, `status`
 * smallint DEFAULT NULL, `status_date` timestamp NULL DEFAULT NULL, PRIMARY KEY
 * (`id`) ) ;
 */