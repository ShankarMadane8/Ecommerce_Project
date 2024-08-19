package com.ecommerce.entity;

import java.util.List;

import com.ecommerce.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private int id;
	@Column(unique = true)
	private String email;
	private String firstName;
	private String lastName;
	private Long phoneNo;
	private String password;
	private String role;
	private String userType;
	
	 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<Address> addresses;




	public User() {
	}

	// Parameterized constructor with UserDto
	public User(UserDto userDto) {
		this.email = userDto.getEmail();
		this.firstName = userDto.getFirstName();
		this.lastName = userDto.getLastName();
		this.phoneNo = Long.parseLong(userDto.getPhoneNo());
		this.role = userDto.getRole();
		this.userType = userDto.getUserType();
		this.password = userDto.getPassword();
		this.addresses = userDto.getAddresses();
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	
	
}
