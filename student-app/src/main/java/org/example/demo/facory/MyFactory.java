package org.example.demo.facory;

import org.example.demo.model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MyFactory {
	
	private static  SessionFactory sessionFactory=null;
	
	
	public static  SessionFactory getSessionFactory()
	{
		sessionFactory=new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
		return sessionFactory;
	}

}
