package org.example.demo.service;

import java.util.List;

import org.example.demo.dao.CustomerDao;
import org.example.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
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

}
