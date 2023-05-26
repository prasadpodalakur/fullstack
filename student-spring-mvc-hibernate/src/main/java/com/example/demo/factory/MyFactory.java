package com.example.demo.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.demo.model.Student;

public class MyFactory {
	
	private static  SessionFactory sessionFactory=null;
	
	
	public static  SessionFactory getSessionFactory()
	{
		sessionFactory=new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
		return sessionFactory;
	}

}
