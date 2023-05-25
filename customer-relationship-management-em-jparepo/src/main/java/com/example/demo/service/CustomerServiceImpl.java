package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerDao customerDao; 
	
	public CustomerServiceImpl(CustomerDao customerDao) {
		super();
		this.customerDao = customerDao;
		System.out.println("************using JpaRepository******************");
	}

	@Override
	public List<Customer> getAllCustomers() {
		
		return customerDao.findAll();
	}


	@Override
	public Customer createCustomer(Customer customer) {
		return customerDao.save(customer);
	}


	@Override
	public Optional<Customer> getCustomer(int theId) {
		// TODO Auto-generated method stub
		Optional<Customer> customer= customerDao.findById(theId);
		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {
		customerDao.deleteById(theId);
	}
}
