package com.example.demo.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.example.demo.factory.MyFactory;
import com.example.demo.model.Student;

@Component("studentDao")
public class StudentDaoImpl implements StudentDao {
	
	private Session session;
	
	{
		session = MyFactory.getSessionFactory().openSession();
	}

	@Override
	@Transactional
	public List<Student> getAllStudents()  throws SQLException {
		Query query = session.createQuery("FROM Student", Student.class);
		@SuppressWarnings("unchecked")
		List<Student> students=query.getResultList();
		return students;
	}

	@Override
	@Transactional
	public Student createStudent(Student student)  throws SQLException {
		session.save(student);
		return student;
	}

	@Override
	@Transactional
	public Student findStudent(int theId)  throws SQLException {
		Student student = session.find(Student.class, theId);
		return student;
	}
	
	@Override
	@Transactional
	public List<Student> findStudent(String name)  throws SQLException {
		Query query=session.createQuery("FROM Student where name = :name",Student.class);
		query.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<Student> students = query.getResultList();
		return students;
	}

	@Override
	@Transactional
	public List<Student> findStudent(Date enteringDate)  throws SQLException {
		Query query=session.createQuery("FROM Student where entering_date = :enteringDate",Student.class);
		query.setParameter("enteringDate", enteringDate);
		@SuppressWarnings("unchecked")
		List<Student> students = query.getResultList();
		return students;
	}

	@Override
	@Transactional
	public Student updateStudent(Student student)  throws SQLException {
		session.update(student);
		return student;
	}
	
	@Override
	@Transactional
	public Student updateStudent(int theId)  throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@Transactional
	public Student updateStudent(String name)  throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	public void deleteStudent(int theId)  throws SQLException {
		Student student = session.get(Student.class, theId);
		session.remove(student);		
	}

	@Override
	@Transactional
	public void deleteAll()  throws SQLException {
		Query query=session.createQuery("FROM Student",Student.class);
		@SuppressWarnings("unchecked")
		List<Student> students=query.getResultList();
		for(Student student: students) {
			session.remove(student);
		}
	}

}
