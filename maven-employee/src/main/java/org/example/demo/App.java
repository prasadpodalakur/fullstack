package org.example.demo;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.example.demo.dao.EmployeeDao;
import org.example.demo.dao.EmployeeDaoImpl;
import org.example.demo.model.Employee;

/**
 * Hello world!
 *
 */
public class App {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		int choice = 0;

		do {

			System.out.println("1. Create New employee.");
			System.out.println("2. Display all Available employee.");
			System.out.println("3. Delete A employee By employee ID.");
			System.out.println("4. Update A employee By employee ID.");
			System.out.println("5. Find A employee By employee ID.");
			System.out.println("6. Create number of employees.");
			System.out.println("0. Exit.");
			System.out.print("Enter Your Choice: ");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.print("Enter First Name: ");
				String fName = scanner.next();
				System.out.print("Enter Last Name: ");
				String lName = scanner.next();
				System.out.print("Enter Email: ");
				String email = scanner.next();
				Employee employee = employeeDao
						.createEmployee(new Employee(new Random().nextInt(1000), fName, lName, email));

				System.out.println("Employee Created: " + employee);
				break;
			case 2:

				List<Employee> list = employeeDao.getAllEmployees();
				if(list.isEmpty())
				{
					System.out.println("employee table is empty.");
				}

				else
				{
					for (Employee c : list) {
						System.out.println(c);
					}
				}
				
				break;
			case 3:

				System.out.print("enter id: ");

				int id = scanner.nextInt();
				employeeDao.deleteEmployeeById(id);
				break;
			case 4:
				System.out.print("Enter employee id: ");
				id=scanner.nextInt();
				System.out.print("Enter First Name: ");
				fName = scanner.next();
				System.out.print("Enter Last Name: ");
				lName = scanner.next();
				System.out.print("Enter Email: ");
				email = scanner.next();
				employeeDao.updateEmployee(id, fName, lName, email);
				break;
			case 5:
				System.out.print("enter id: ");
				id=scanner.nextInt();
				employeeDao.findEmployeeByid(id);
				break;
			case 6:
				System.out.print("Enter Number of employees required: ");
				int empCount = scanner.nextInt();
				
				
				for(int i=0;i<empCount;i++) {
					fName = "FNAME"+i;
					lName = "LNAME"+i;
					email = "EMAIL"+i;
					
					employee = employeeDao
							.createEmployee(new Employee(new Random().nextInt(1000), fName, lName, email));
					System.out.println("Employee Created: " + employee);
				}
								
				break;	
			case 0:
				System.out.println("bye...");
				System.exit(1);
				break;

			default:
				System.out.println("Invalid choice try again");
				break;
			}

		} while (choice != 0);

	}
}
