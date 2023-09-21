package com.spring.shop.service;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "your_id_column", nullable = false)
    private long id;
	
	private String CustomerName;
	private Long contact;
	private String Addrses;
	
	@OneToMany(mappedBy="customer")
	private List<Product> products;

	public String getCustomerName() {
		return CustomerName;
	}

	public Long getContact() {
		return contact;
	}

	public String getAddrses() {
		return Addrses;
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

	public void setAddrses(String addrses) {
		Addrses = addrses;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Customer(String customerName, Long contact, String addrses, List<Product> products) {
		super();
		CustomerName = customerName;
		this.contact = contact;
		Addrses = addrses;
		this.products = products;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
