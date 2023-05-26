package com.example.demo.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Student;

@Repository
@EnableTransactionManagement
@Primary
public class StudentDaoImpl implements StudentDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public List<Student> getAllStudents() {
		Query query=entityManager.createQuery("FROM Student",Student.class);
		@SuppressWarnings("unchecked")
		List<Student> students=query.getResultList();
		return students;
	}

	@Override
	@Transactional
	public Student createStudent(Student student) {
		entityManager.merge(student);
		return student;
	}

	@Override
	@Transactional
	public Student findStudent(int theId) {
		Student student = entityManager.find(Student.class, theId);
		return student;
	}
	
	@Override
	@Transactional
	public List<Student> findStudent(String name) {
		Query query=entityManager.createQuery("FROM Student where name = :name",Student.class);
		query.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<Student> students = query.getResultList();
		return students;
	}

	@Override
	@Transactional
	public List<Student> findStudent(Date enteringDate) {
		Query query=entityManager.createQuery("FROM Student where entering_date = :enteringDate",Student.class);
		query.setParameter("enteringDate", enteringDate);
		@SuppressWarnings("unchecked")
		List<Student> students = query.getResultList();
		System.out.println("students:"+students);
		return students;
	}

	@Override
	@Transactional
	public Student updateStudent(Student student) {
		entityManager.merge(student);
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
		Student student = entityManager.find(Student.class, theId);
		entityManager.remove(student);		
	}

	@Override
	@Transactional
	public void deleteAll() {
		Query query=entityManager.createQuery("FROM Student",Student.class);
		@SuppressWarnings("unchecked")
		List<Student> students=query.getResultList();
		for(Student student: students) {
			entityManager.remove(student);
		}
	}

}
