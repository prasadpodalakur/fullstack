package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CustomerDao;
import com.example.demo.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerDao customerDao;
	
	public CustomerServiceImpl(CustomerDao customerDao) {
		super();
		this.customerDao = customerDao;
	}

	@Override
	@Transactional
	public List<Customer> getAllCustomers() {
		
		return customerDao.getAllCustomers();
	}


	@Override
	@Transactional
	public Customer createCustomer(Customer customer) {
		return customerDao.createCustomer(customer);
	}


	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		return customerDao.getCustomer(theId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		customerDao.deleteCustomer(theId);
	}
}
