package com.example.demo.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.example.demo.model.Student;

/**
 *  Add Student
	Display all students
	find student by id
	find student by name
	find student by entering date
	update student by id
	update student by name
	delete by id
	delete all
	
 * @author Administrator
 *
 */
public interface StudentDao {
	
	public Student createStudent(Student student)  throws SQLException;
	
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
