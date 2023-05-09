package org.example.demo.dao;

import java.sql.SQLException;
import java.util.List;

import org.example.demo.model.Employee;

public interface EmployeeDao {
	
	public Employee createEmployee(Employee employee) throws SQLException;

	public List<Employee> getAllEmployees() throws SQLException;
	
	public void deleteEmployeeById(int employeeId) throws SQLException;
	
	public void updateEmployee(int employeeId,String firstName,String lastName,String email) throws SQLException;
	
	public void findEmployeeByid(int employeeId) throws SQLException;
	
}
