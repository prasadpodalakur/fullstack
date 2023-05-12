package org.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.example.demo.dao.CustomerDao;
import org.example.demo.model.Customer;
import org.springframework.stereotype.Component;

@Component("customerService")
public class CustomerServiceImpl implements CustomerService{

	
	private final CustomerDao customerDao;

	public CustomerServiceImpl(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerDao.getAllCustomers();
	}


	@Override
	public Customer createCustomer(Customer customer) throws SQLException {

		customerDao.createCustomer(customer);
		
		return customer;
		
	}


	@Override
	public Customer findByCustomerId(int customerId) throws SQLException {

		Customer customer = customerDao.findByCustomerId(customerId);
		return customer;
	}

	@Override
	public int deleteByCustomerId(int customerId) throws SQLException {
		 
		return customerDao.deleteByCustomerId(customerId);
		
	}

}
