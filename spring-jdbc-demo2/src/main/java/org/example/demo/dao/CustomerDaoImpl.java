package org.example.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.example.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("customerDao")
public class CustomerDaoImpl implements CustomerDao {

	private final DataSource dataSource;

	@Autowired
	public CustomerDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> list=new ArrayList<Customer>();
		try {
			Connection connection=dataSource.getConnection();
			
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery("select * from customer");
			while(resultSet.next())
			{
			 list.add(new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));	
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

}
