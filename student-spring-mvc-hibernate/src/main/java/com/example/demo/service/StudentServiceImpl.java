package com.example.demo.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dao.StudentDao;
import com.example.demo.model.Student;

@Component("studentService")
public class StudentServiceImpl implements StudentService {
	
	private final StudentDao studentDao;
	
	public StudentServiceImpl(StudentDao studentDao) {
		super();
		this.studentDao = studentDao;
	}

	@Override
	public List<Student> getAllStudents()  throws SQLException{
		return studentDao.getAllStudents();
	}

	@Override
	public Student createStudent(Student student)  throws SQLException{
		return studentDao.createStudent(student);
	}

	@Override
	public Student findStudent(int theId)  throws SQLException{
		return studentDao.findStudent(theId);
	}
	

	@Override
	public List<Student> findStudent(String name)  throws SQLException{
		return studentDao.findStudent(name);
	}

	@Override
	public List<Student> findStudent(Date date) throws SQLException {
		return studentDao.findStudent(date);
	}

	@Override
	public Student updateStudent(Student student) throws SQLException {
		return studentDao.updateStudent(student);
	}
	
	@Override
	public Student updateStudent(int theId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student updateStudent(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStudent(int theId) throws SQLException {
		studentDao.deleteStudent(theId);	
	}

	@Override
	public void deleteAll() throws SQLException {
		studentDao.deleteAll();
	}
	
	
}
