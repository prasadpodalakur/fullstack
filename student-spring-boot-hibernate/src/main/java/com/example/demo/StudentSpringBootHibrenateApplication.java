package com.example.demo;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Student;

@SpringBootApplication
public class StudentSpringBootHibrenateApplication implements CommandLineRunner {
	
	private final SessionFactory sessionFactory;
	
	public StudentSpringBootHibrenateApplication(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public static void main(String[] args) {
		SpringApplication.run(StudentSpringBootHibrenateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Session session=sessionFactory.openSession();
		session.getTransaction().begin();
		session.save(new Student("John1", Date.valueOf("2023-05-23"), "IND", "CODE1"));
		session.save(new Student("John2", Date.valueOf("2023-05-24"), "IND", "CODE2"));
		session.save(new Student("John3", Date.valueOf("2023-05-25"), "IND", "CODE3"));
		session.getTransaction().commit();
		
	}

}
