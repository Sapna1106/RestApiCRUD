package com.spring.shop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.shop.repository.CustomerRepository;
import com.spring.shop.repository.ProductRepository;
import com.spring.shop.service.Customer;
import com.spring.shop.service.Product;

@RestController
@RequestMapping("/customers")
public class CustomerController {
//	@GetMapping("/hello")
//	public String getAllCustomers() {
//      return "Hello!";
//  }

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
     
        	return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Long id) {
    	Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
        	try {
	        	customerRepository.deleteById(id);
	            return ResponseEntity.ok("Customer with ID " + id + " deleted successfully.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
	        }
        } else {
            return ResponseEntity.ok("Customer with ID "+id+" is not found");
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable Long id,@RequestBody Customer updatedCustomerData) {
    	 Optional<Customer> presentCustomer = customerRepository.findById(id);
         if (presentCustomer.isPresent()) {
        	 Customer customer = presentCustomer.get();
        	 
        	 customer.setCustomerName(updatedCustomerData.getCustomerName());
        	 customer.setContact(updatedCustomerData.getContact());
        	 customer.setAddrses(updatedCustomerData.getAddrses());
        	 customer = customerRepository.save(customer);
        	 return ResponseEntity.ok(customer);
         } else {
             return ResponseEntity.notFound().build();
         }
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Customer> partialUpdateCustomer(@PathVariable Long id,@RequestBody Customer updatedCustomerData) {
    	 Optional<Customer> presentCustomer = customerRepository.findById(id);
         if (presentCustomer.isPresent()) {
        	 Customer customer = presentCustomer.get();
        	 
        	 if(updatedCustomerData.getCustomerName()!=null)
        		 customer.setCustomerName(updatedCustomerData.getCustomerName());
        	 if(updatedCustomerData.getContact()!=null)
        		 customer.setContact(updatedCustomerData.getContact());
        	 if(updatedCustomerData.getAddrses()!=null)
        		 customer.setAddrses(updatedCustomerData.getAddrses());
        	 
        	 customer = customerRepository.save(customer);
        	 return ResponseEntity.ok(customer);
         } else {
             return ResponseEntity.notFound().build();
         }
    }
    

    
    
    @GetMapping("/{cId}/products")
    public List<Product> getProductsByUser(@PathVariable Long cId) {
        Optional<Customer> customerId =customerRepository.findById(cId);
        if(customerId.isPresent()) {
        	Customer customer=customerId.get();
        	return customer.getProducts();
        }else {
        	return null;
        }
        
    }
    
    
    @GetMapping("/{cId}/products/{pId}")
    public Product getProductsByUserByProductId(@PathVariable("cId")Long cId,@PathVariable("pId") Long pId) {
        Optional<Customer> customerId =customerRepository.findById(cId);
        if(customerId.isPresent()) {
        	Optional<Product> productId=productRepository.findById(pId);
        	if(productId.isPresent()) {
        		Product product=productId.get();
        		return product;
        	}
        	return null;
        	
        }else {
        	return null;
        }
        
    }
    

    @PostMapping("/{id}/products")
    public ResponseEntity<Product> createProduct(@PathVariable Long id, @RequestBody Product product) {
    	
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
        	System.out.println(product);
            Customer customer = customerOptional.get();
            product.setCustomer(customer);
            customer.getProducts().add(product);
            customerRepository.save(customer);
           
            Product savedProduct = productRepository.save(product);
            return ResponseEntity.ok(savedProduct);
        } else {
            // Handle the case where the customer with the given ID is not found
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    	Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
        	try {
	        	productRepository.deleteById(id);
	            return ResponseEntity.ok("Product with ID " + id + " deleted successfully.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
	        }
        } else {
            return ResponseEntity.ok("Product with ID "+id+" is not found");
        }
    }
    
    @PutMapping("/{cId}/products/{pId}")
    public ResponseEntity<Product> updateProductById(@PathVariable ("cId")Long cId,@PathVariable ("pId")Long pId,@RequestBody Product updatedProductData) {
    	 Optional<Product> presentProduct = productRepository.findById(pId);
    	 Optional<Customer> presentCustomer = customerRepository.findById(cId);
         if (presentProduct.isPresent() && presentCustomer.isPresent()){
        	 Product product = presentProduct.get();
        	 product.setProducPrice(updatedProductData.getProducPrice());
        	 product.setProductNAme(updatedProductData.getProductNAme());
        	 product.setProductDesc(updatedProductData.getProductDesc());
        	 product.setRating(updatedProductData.getRating());
        	 product = productRepository.save(product);
        	 return ResponseEntity.ok(product);
         } else {
             return ResponseEntity.notFound().build();
         }
    }
    
    @PatchMapping("/{cId}/products/{pId}")
    public ResponseEntity<Product> partialUpdate(@PathVariable ("cId")Long cId,@PathVariable ("pId")Long pId,@RequestBody Product updatedProductData) {
    	 Optional<Product> presentProduct = productRepository.findById(pId);
    	 Optional<Customer> presentCustomer = customerRepository.findById(cId);
         if (presentProduct.isPresent() && presentCustomer.isPresent()){
        	 Product product = presentProduct.get();
        	 if(updatedProductData.getProducPrice()!=null)
        		 product.setProducPrice(updatedProductData.getProducPrice());
        	 if(updatedProductData.getProductNAme()!=null)
        		 product.setProductNAme(updatedProductData.getProductNAme());
        	 if(updatedProductData.getProductDesc()!=null)
        		 product.setProductDesc(updatedProductData.getProductDesc());
        	 if(updatedProductData.getRating()!=0)
        		 product.setRating(updatedProductData.getRating());
        	 product = productRepository.save(product);
        	 return ResponseEntity.ok(product);
         } else {
             return ResponseEntity.notFound().build();
         }
    }
    
}



