package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Customer;

public interface CustomerService {
	
	
	public List<Customer> getAllCustomers();
	
	public Customer createCustomer(Customer customer);
	
	public Optional<Customer> getCustomer(int theId);

	public void deleteCustomer(int theId);

}
