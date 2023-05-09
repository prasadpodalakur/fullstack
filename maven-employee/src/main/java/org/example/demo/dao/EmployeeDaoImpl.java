package org.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.example.demo.factory.MySqlConnectionFactory;
import org.example.demo.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	private Connection connection;

	{
		try {
			connection = MySqlConnectionFactory.getMySqlconnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Employee createEmployee(Employee employee) throws SQLException {
		// TODO Auto-generated method stub

		PreparedStatement preparedStatement = connection.prepareStatement("insert into employee values(?,?,?,?)");
		preparedStatement.setInt(1, employee.getEmployeeId());
		preparedStatement.setString(2, employee.getFirstName());
		preparedStatement.setString(3, employee.getLastName());
		preparedStatement.setString(4, employee.getEmail());
		preparedStatement.executeUpdate();
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() throws SQLException {
		// TODO Auto-generated method stub
		List<Employee> list = new ArrayList<Employee>();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from employee");

		while (resultSet.next()) {
			list.add(new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4)));
		}
		return list;
	}

	@Override
	public void deleteEmployeeById(int employeeId) throws SQLException {
		// TODO Auto-generated method stub

		PreparedStatement preparedStatement = connection.prepareStatement("delete  from employee where employee_id=?");
		preparedStatement.setInt(1, employeeId);
		int count = preparedStatement.executeUpdate();
		if (count > 0) {
			System.out.println("employee with " + employeeId + " removed sucessfully...");
		} else {
			System.out.println("no such record found........!!");
		}

	}

	@Override
	public void updateEmployee(int employeeId,String firstName,String lastName,String email) throws SQLException {
		// TODO Auto-generated method stub
		
		PreparedStatement preparedStatement=connection.prepareStatement("update employee set first_name=?,last_name=?,email=? where employee_id=?");
		preparedStatement.setString(1, firstName);
		preparedStatement.setString(2, lastName);
		preparedStatement.setString(3, email);
		preparedStatement.setInt(4, employeeId);
		int count=preparedStatement.executeUpdate();
		
		if(count>0)
		{
			System.out.println("employee updation sucessfull...");
		}
		else
		{
			System.out.println("employee record not found..!");
		}
		
	}

	@Override
	public void findEmployeeByid(int employeeId) throws SQLException {
		// TODO Auto-generated method stub
		
		PreparedStatement preparedStatement=connection.prepareStatement("select * from employee where employee_id=?");
		preparedStatement.setInt(1, employeeId);
		ResultSet resultSet=preparedStatement.executeQuery();
		
		if(resultSet.next())
		{
			System.out.println("found.");
			System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+ " "+resultSet.getString(4));
		}
		else
		{
			System.out.println("not found.");
		}
			
		
		
		
	}

}
