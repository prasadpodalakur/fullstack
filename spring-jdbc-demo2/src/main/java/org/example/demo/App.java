package org.example.demo;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.example.demo.config.SpringConfig;
import org.example.demo.dao.CustomerDao;
import org.example.demo.model.Customer;
import org.example.demo.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		try {

			ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

			CustomerService customerService = context.getBean("customerService", CustomerService.class);
			int choice =0;
			do {
				System.out.println("1. getAllCustomers");
				System.out.println("2. createCustomer");
				System.out.println("3. findByCustomerId");
				System.out.println("4. deleteByCustomerId");
				
				System.out.println("Enter option:");
				choice = scanner.nextInt();
				switch (choice) {
				case 1:
					List<Customer> list = customerService.getAllCustomers();
					for (Customer c : list) {
						System.out.println(c);
					}
					break;
				case 2:
					 int customerId = new Random().nextInt(1000);
					 System.out.println("Enter First Name:"); String firstName = scanner.next();
					 System.out.println("Enter Last Name:"); String lastName = scanner.next();
					 System.out.println("Enter Email:"); String email = scanner.next(); Customer
					 cust = new Customer(customerId, firstName, lastName, email);
					 customerService.createCustomer(cust);
					 System.out.println("Customer has been created successfully :"+cust);
					 break;
				case 3:
					
					System.out.println("Enter Customer Id :");
					customerId = scanner.nextInt();
					cust = customerService.findByCustomerId(customerId);
					if(cust == null) {
						System.out.println("No customer found for the given Id :"+customerId);
					}else {
						System.out.println(cust);
					}
					
					break;
					case 4:
					
						try {
							System.out.println("Enter Customer Id to delete :");
							customerId = scanner.nextInt();
							
							int count = customerService.deleteByCustomerId(customerId);
							if(count <= 0) {
								System.out.println("No customer found for the given Id :"+customerId);
							}else {
								System.out.println("customer has been deleted successfully for the Id:"+customerId);
							}
							
						}catch(InputMismatchException e) {
							System.err.println("Invalid input..");
						}
						break;
					
				case 0:
					System.out.println("BYE...");
					System.exit(1);
					break;
	
				default:
					System.out.println("Invalid choice try again ......");
					break;
					
				}
			}while (choice != 0);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
