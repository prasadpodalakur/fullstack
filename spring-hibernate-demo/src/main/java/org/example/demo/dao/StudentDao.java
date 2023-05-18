package org.example.demo.dao;

import java.util.List;

import org.example.demo.model.Student;


public interface StudentDao {
	
	public List<Student> getAllStudents();
	public Student getStudentById(int studentId);
	public Student createStudent(Student student);
	public Student updateStudent(int studentId, Student student);
	public void deleteStudentById(int studentId);
	public void deleteAllStudents();

}
