package com.example.demo.dao;

import java.sql.Date;
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
	
	public Student createStudent(Student student);
	
	public List<Student> getAllStudents();
	
	public Student findStudent(int theId);
	
	public List<Student> findStudent(String name);
	
	public List<Student> findStudent(Date date);
	
	public Student updateStudent(int theId);
	
	public Student updateStudent(String name);
	
	public Student updateStudent(Student student);
	
	public void deleteStudent(int theId);
	
	public void deleteAll();

}
