package com.Ecommerce.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
@Entity 
public class User {

		@GeneratedValue(strategy = GenerationType.AUTO)
		@Id
		int id;
		String address;
		@Column(unique = true)
		String email;
		int failed_logins;
		int last_login;
		String name;
		String password;
		String phone_no;
		int status;
		int type;
		String userType;
		
		int pinCode;
		
		public int getPinCode() {
			return pinCode;
		}
		public void setPinCode(int pinCode) {
			this.pinCode = pinCode;
		}
		@OneToMany(cascade = CascadeType.ALL )
		@JoinColumn(name="user_id")
		List<Address> addresses;
		
		
		public List<Address> getAddresses() {
			return addresses;
		}
		public void setAddresses(List<Address> addresses) {
			this.addresses = addresses;
		}
		public String getUserType() {
			return userType;
		}
		public void setUserType(String userType) {
			this.userType = userType;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public int getFailed_logins() {
			return failed_logins;
		}
		public void setFailed_logins(int failed_logins) {
			this.failed_logins = failed_logins;
		}
		public int getLast_login() {
			return last_login;
		}
		public void setLast_login(int last_login) {
			this.last_login = last_login;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPhone_no() {
			return phone_no;
		}
		public void setPhone_no(String phone_no) {
			this.phone_no = phone_no;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", address=" + address + ", email=" + email + ", failed_logins=" + failed_logins
					+ ", last_login=" + last_login + ", name=" + name + ", password=" + password + ", phone_no="
					+ phone_no + ", status=" + status + ", type=" + type + ", userType=" + userType + ", addresses="
					+ addresses + "]";
		}
		
		
		
		
}
