package org.example.demo.student.dao;

import java.util.List;

import org.example.demo.student.model.Student;


public interface StudentDao {
	
	public List<Student> getAllStudents();
	public Student getStudentById(int studentId);
	public Student createStudent(Student student);
	public Student updateStudent(int studentId, Student student);
	public void deleteStudentById(int studentId);
	public void deleteAllStudents();

}
