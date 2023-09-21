package com.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.shop.service.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
