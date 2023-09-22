package com.spring.shop.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)@Column(name = "customerId", nullable = false)
    private long id;
	
	private String CustomerName;
	private Long contact;
	private String address;
	
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Product> products;

	public String getCustomerName() {
		return CustomerName;
	}

	public Long getContact() {
		return contact;
	}

	public String getAddress() {
		return address;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Customer(String customerName, Long contact, String address, List<Product> products) {
		super();
		CustomerName = customerName;
		this.contact = contact;
		this.address = address;
		this.products = products;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
