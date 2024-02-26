package com.Ecommerce.Entity;

import org.hibernate.annotations.DialectOverride.GeneratedColumn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	int id;
	@Column(unique = true)
	int customerId;
	int orderhdId;
	double amount;
	int mode;
	int cardType;
	String cardNo;
	String cardHolderName;
	String cvv;
	String validTill;
	String upiId;
	int transactionId;
	String bankName;
	String ifsc;
	String bankAccountNo;
	String voucherNo;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public int getCardType() {
		return cardType;
	}
	public void setCardType(int cardType) {
		this.cardType = cardType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getValidTill() {
		return validTill;
	}
	public void setValidTill(String validTill) {
		this.validTill = validTill;
	}
	public String getUpiId() {
		return upiId;
	}
	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
	
	
}

