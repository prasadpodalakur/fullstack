package com.example.demo.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.example.demo.model.Student;

public interface StudentService {
	
	
	public Student createStudent(Student student) throws SQLException;
	
	public List<Student> getAllStudents() throws SQLException;
	
	public Student findStudent(int theId) throws SQLException;
	
	public List<Student> findStudent(String name) throws SQLException;
	
	public List<Student> findStudent(Date date) throws SQLException;
	
	public Student updateStudent(int theId) throws SQLException;
	
	public Student updateStudent(String name) throws SQLException;
	
	public Student updateStudent(Student student) throws SQLException;
	
	public void deleteStudent(int theId) throws SQLException;
	
	public void deleteAll() throws SQLException;

}
