package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Customer;

public interface CustomerService {
	
	
	public List<Customer> getAllCustomers();
	
	public Customer createCustomer(Customer customer);
	
	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

}
