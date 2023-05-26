package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.StudentDao;
import com.example.demo.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	private final StudentDao studentDao;
	
	public StudentServiceImpl(StudentDao studentDao) {
		super();
		this.studentDao = studentDao;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}

	@Override
	public Student createStudent(Student student) {
		return studentDao.createStudent(student);
	}

	@Override
	public Student findStudent(int theId) {
		return studentDao.findStudent(theId);
	}
	

	@Override
	public List<Student> findStudent(String name) {
		return studentDao.findStudent(name);
	}

	@Override
	public List<Student> findStudent(Date date) {
		return studentDao.findStudent(date);
	}

	@Override
	public Student updateStudent(Student student) {
		return studentDao.updateStudent(student);
	}
	
	@Override
	public Student updateStudent(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student updateStudent(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void deleteStudent(int theId) {
		studentDao.deleteStudent(theId);	
	}

	@Override
	public void deleteAll() {
		studentDao.deleteAll();
	}
	
	
}
