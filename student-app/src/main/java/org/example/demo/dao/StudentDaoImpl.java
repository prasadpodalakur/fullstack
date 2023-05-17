package org.example.demo.dao;

import org.example.demo.facory.MyFactory;
import org.example.demo.model.Student;
import org.hibernate.Session;

public class StudentDaoImpl implements StudentDao{
	
	private Session session;
	
	{
		session=MyFactory.getSessionFactory().openSession();
	}

	@Override
	public Student createStudent(Student student) {
		// TODO Auto-generated method stub
		session.save(student);
		return student;
	}
	

}
