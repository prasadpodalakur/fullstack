package org.example.todo.factory;

import org.example.todo.model.ToDo;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MyFactory {
	
	private static  SessionFactory sessionFactory=null;
	
	public static  SessionFactory getSessionFactory()
	{
		sessionFactory=new Configuration().configure().addAnnotatedClass(ToDo.class).buildSessionFactory();
		return sessionFactory;
	}

}