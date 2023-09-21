package com.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.shop.service.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
