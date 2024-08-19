package com.ecommerce.entity;

import jakarta.persistence.*;


@Entity
public class Product {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;
	
	@Column(length = 500)
	private String name;
	private String brand;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private byte[] image;

	@Column(length = 1500) 
	private String description;
	private float price;
	private float discount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}
}
