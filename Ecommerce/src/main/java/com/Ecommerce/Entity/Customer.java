package com.Ecommerce.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
	 @GeneratedValue(strategy = GenerationType.AUTO)
     @Id
     int id;
     int dob;
     String address;
     int age;
     String contactno;
     String created_by;
     int created_date;
     @Column(unique = true)
     String email;
     int failed_login;
     String fname;
     String gender;
     int last_login;
     String lname;
     String password;
     String photo;
     int type;
     String updated_by;
     int updated_date;
     String username;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDob() {
		return dob;
	}
	public void setDob(int dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public int getCreated_date() {
		return created_date;
	}
	public void setCreated_date(int created_date) {
		this.created_date = created_date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getFailed_login() {
		return failed_login;
	}
	public void setFailed_login(int failed_login) {
		this.failed_login = failed_login;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getLast_login() {
		return last_login;
	}
	public void setLast_login(int last_login) {
		this.last_login = last_login;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	public int getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(int updated_date) {
		this.updated_date = updated_date;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
     
     
     
}
