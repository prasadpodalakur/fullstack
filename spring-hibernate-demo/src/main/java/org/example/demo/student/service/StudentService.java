package org.example.demo.student.service;

import java.util.List;

import org.example.demo.student.model.Student;

public interface StudentService {
	
	public List<Student> getAllStudents();
	public Student getStudentById(int studentId);
	public Student createStudent(Student student);
	public Student updateStudent(int studentId, Student student);
	public void deleteStudentById(int studentId);
	public void deleteAllStudents();
	
}
