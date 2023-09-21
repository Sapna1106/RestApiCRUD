package com.spring.shop.service;

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
    @Column(name = "your_id_column", nullable = false)
    private long id;
	
	private String productNAme;
	private String productDesc;
	private String producPrice;
	private int rating;
	
	@ManyToOne
	@JsonIgnore
	private Customer customer;

	public String getProductNAme() {
		return productNAme;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public String getProducPrice() {
		return producPrice;
	}

	public int getRating() {
		return rating;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setProductNAme(String productNAme) {
		this.productNAme = productNAme;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public void setProducPrice(String producPrice) {
		this.producPrice = producPrice;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product(String productNAme, String productDesc, String producPrice, int rating, Customer customer) {
		super();
		this.productNAme = productNAme;
		this.productDesc = productDesc;
		this.producPrice = producPrice;
		this.rating = rating;
		this.customer = customer;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productNAme=" + productNAme + ", productDesc=" + productDesc + ", producPrice="
				+ producPrice + ", rating=" + rating + ", customer=" + customer + "]";
	}
	
	
	

}
