package org.example.demo.service;

import java.util.List;

import org.example.demo.dao.StudentDao;
import org.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("studentService")
public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao;
	
	@Autowired
	public StudentServiceImpl(StudentDao studentDao) {

		this.studentDao = studentDao;
		
	}

	@Override
	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}

	@Override
	public Student getStudentById(int studentId) {
		return studentDao.getStudentById(studentId);
	}

	@Override
	public Student createStudent(Student student) {
		return studentDao.createStudent(student);
	}
	
	@Override
	public Student updateStudent(int studentId, Student student) {
		return studentDao.updateStudent(studentId, student);
	}
	
	@Override
	public void deleteStudentById(int studentId) {
		studentDao.deleteStudentById(studentId);
	}

	@Override
	public void deleteAllStudents() {
		studentDao.deleteAllStudents();
	}

}
