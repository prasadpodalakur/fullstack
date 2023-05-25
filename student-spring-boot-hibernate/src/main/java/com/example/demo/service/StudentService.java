package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import com.example.demo.model.Student;

public interface StudentService {
	
	
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
