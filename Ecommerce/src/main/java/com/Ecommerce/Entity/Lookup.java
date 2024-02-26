package com.Ecommerce.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Lookup {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	int id;
	String enabled;
	String fixed;
	int icode;
	String scode;
	String type;
	String value;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getFixed() {
		return fixed;
	}
	public void setFixed(String fixed) {
		this.fixed = fixed;
	}
	public int getIcode() {
		return icode;
	}
	public void setIcode(int icode) {
		this.icode = icode;
	}
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}

//select * from item,lookup where item.category=lookup.icode and lookup.type="category";