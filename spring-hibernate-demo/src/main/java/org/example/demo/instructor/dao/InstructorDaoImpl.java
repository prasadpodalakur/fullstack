package org.example.demo.instructor.dao;

import java.util.List;

import javax.persistence.Query;

import org.example.demo.instructor.model.Instructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysql.cj.util.StringUtils;

@Component("instructorDao")
public class InstructorDaoImpl implements InstructorDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Instructor> getAllInstructors() {
		Query query = getSession().createQuery("FROM Instructor");
		List<Instructor> instructorsList = query.getResultList();
		 
		return instructorsList;
	}


	@Override
	public Instructor getInstructorById(int instructorId) {
		Instructor instructor = getSession().find(Instructor.class, instructorId);
		return instructor;
	}

	@Override
	public Instructor createInstructor(Instructor instructor) {
		
		Session session=getSession();
		session.getTransaction().begin();
		
		session.save(instructor);
		
		session.getTransaction().commit();
		
		return instructor;
	}
	
	@Override
	public Instructor updateInstructor(int instructorId, Instructor newInstructor) {
		Session session=getSession();
		session.getTransaction().begin();
		
		Instructor instructor = session.get(Instructor.class, instructorId);
		if(instructor == null) {
			System.out.println("no Instructor found for the id...:"+instructorId);
		}else {
			
			if(!StringUtils.isNullOrEmpty(newInstructor.getInstructorName())) {
				instructor.setInstructorName(newInstructor.getInstructorName());
			}
			if(!StringUtils.isNullOrEmpty(newInstructor.getInstructorRating())) {
				instructor.setInstructorRating(newInstructor.getInstructorRating());
			}
			
			session.save(instructor);
			
			System.out.println("Instructor has been updated...");
		}
		
		session.getTransaction().commit();
		
		return instructor;
	}
	
	@Override
	public void deleteInstructorById(int instructorId) {
		Session session=getSession();
		session.getTransaction().begin();
		
		Instructor instructor = session.get(Instructor.class, instructorId);
		if(instructor == null) {
			System.out.println("no Instructor found for the id...:"+instructorId);
		}else {
			session.delete(instructor);
			System.out.println("Instructor has been deleted...");
		}
		
		session.getTransaction().commit();
	}


	@Override
	public void deleteAllInstructors() {
		Session session=getSession();
		session.getTransaction().begin();
		
		Query query = session.createQuery("FROM Instructor");
		List<Instructor> instructorList = query.getResultList();
		for(Instructor s: instructorList) {
			session.delete(s);
		}
		
		session.getTransaction().commit();
	}
	
	private Session getSession() {
		return sessionFactory.openSession();
	}

}
