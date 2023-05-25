package com.example.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Customer;

@SpringBootApplication
public class CustomerRelationshipManagementApplication implements CommandLineRunner {
	
	private final SessionFactory sessionFactory;
	
	

	public CustomerRelationshipManagementApplication(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public static void main(String[] args) {
		SpringApplication.run(CustomerRelationshipManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Session session=sessionFactory.openSession();
		session.getTransaction().begin();
		session.save(new Customer("John", "Doe", "john@email.com"));
		session.save(new Customer("Marry", "Public", "marry@email.com"));
		session.save(new Customer("Rahul", "Dravid", "rahul@email.com"));
		session.getTransaction().commit();
		
	}

}
