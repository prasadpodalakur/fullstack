package com.example.demo.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Student;

public class StudentDaoImpl2 implements StudentDao {
	
	private final SessionFactory sessionFactory;
	
	
	public StudentDaoImpl2(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}


	@Override
	@Transactional
	public List<Student> getAllStudents() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("FROM Student",Student.class);
		@SuppressWarnings("unchecked")
		List<Student> students=query.getResultList();
		return students;
	}

	@Override
	@Transactional
	public Student createStudent(Student student) {
		Session session=sessionFactory.openSession();
		session.save(student);
		return student;
	}

	@Override
	@Transactional
	public Student findStudent(int theId) {
		Session session=sessionFactory.openSession();
		Student student = session.get(Student.class, theId);
		return student;
	}
	
	@Override
	@Transactional
	public List<Student> findStudent(String name) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("FROM Student",Student.class);
		query.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<Student> students = query.getResultList();
		return students;
	}

	@Override
	@Transactional
	public List<Student> findStudent(Date date) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("FROM Student",Student.class);
		query.setParameter("date", date);
		@SuppressWarnings("unchecked")
		List<Student> students = query.getResultList();
		return students;
	}

	@Override
	@Transactional
	public Student updateStudent(Student student) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(student);
		return student;
	}
	
	@Override
	@Transactional
	public Student updateStudent(int theId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@Transactional
	public Student updateStudent(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	@Transactional
	public void deleteStudent(int theId) {
		Session session=sessionFactory.openSession();
		Student student = session.get(Student.class, theId);
		System.out.println("deleteStudent:"+student);
		session.remove(student);		
	}

	@Override
	@Transactional
	public void deleteAll() {
		Session session=sessionFactory.openSession();
		session.remove(Student.class);
	}

}
