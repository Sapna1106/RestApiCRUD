package com.spring.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "productId", nullable = false)
    private long id;
	
	private String productName;
	private String productDesc;
	private String productPrice;
	private int rating;
	
	@ManyToOne
	@JsonIgnore
	private Customer customer;

	public String getProductName() {
		return productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public int getRating() {
		return rating;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product(String productName, String productDesc, String productPrice, int rating, Customer customer) {
		super();
		this.productName = productName;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.rating = rating;
		this.customer = customer;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", productDesc=" + productDesc + ", productPrice="
				+ productPrice + ", rating=" + rating + ", customer=" + customer + "]";
	}
	
	
	

}
