package com.Ecommerce.Entity;


public class PaymentProcessingDetails {
	Address address1;
	String paymentMode;
	String cardHolderName;
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}


	int addressId;
	String cardNumber;
	String expiryDate;
    String cvv;

	
	public Address getAddress1() {
		return address1;
	}
	public void setAddress1(Address address1) {
		this.address1 = address1;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	@Override
	public String toString() {
		return "PaymentProcessingDetails [address1=" + address1 + ", paymentMode=" + paymentMode + ", cardHolderName="
				+ cardHolderName + ", addressId=" + addressId + ", cardNumber=" + cardNumber + ", expiryDate="
				+ expiryDate + ", cvv=" + cvv + "]";
	}
	
	
	
	
	
	

}
