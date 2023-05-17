package org.example.demo.service;

import org.example.demo.dao.StudentDao;
import org.example.demo.dao.StudentDaoImpl;
import org.example.demo.model.Student;

public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao;
	
	{
		studentDao=new StudentDaoImpl();
	}

	@Override
	public Student createStudent(Student student) {
		// TODO Auto-generated method stub
		return studentDao.createStudent(student);
	}

}
