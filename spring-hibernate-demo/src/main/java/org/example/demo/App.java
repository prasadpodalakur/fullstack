package org.example.demo;

import java.util.Random;
import java.util.Scanner;

import org.example.demo.config.SpringConfig;
import org.example.demo.model.Student;
import org.example.demo.service.StudentServiceImpl;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
	static Scanner scanner = new Scanner(System.in);
	
    public static void main( String[] args )
    {
    	System.out.println( "Hello Student World!" );
    	
    	
    	/**
		ApplicationContext context=new
		AnnotationConfigApplicationContext(SpringConfig.class); SessionFactory
		sessionFactory=context.getBean("sessionFactory",SessionFactory.class);
		Session session=sessionFactory.openSession();
		session.getTransaction().begin();
		session.save(new Student(201, "Sachin", "Tendulkar", "sachin@email.com")); 
		session.getTransaction().commit();
		System.out.println("done...");
		*/
        
        
        int choice=0;
        try {
        do {
        	
        	ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        	StudentServiceImpl studentService = context.getBean("studentService",StudentServiceImpl.class);
        		
        	System.out.println("1. getAllStudents");
			System.out.println("2. getStudentById");
			System.out.println("3. createStudent");
			System.out.println("4. updateStudent");
			System.out.println("5. deleteStudentById");
			System.out.println("6. deleteAllStudents");
        	
			System.out.println("Enter option:");
			choice = scanner.nextInt();
			
			
			switch (choice) {
			case 1:
				java.util.List<Student> studentsList = studentService.getAllStudents();
				
				if(studentsList.isEmpty()) {
					System.out.println("No students found .....");
				}
		        for(Student s:studentsList)
		        {
		        	System.out.println(s);
		        }
				break;
				
			case 2:
				
				System.out.println("Enter Student Id :");
				int studentId = scanner.nextInt();
				
				Student student = studentService.getStudentById(studentId);
				if(student == null) {
					System.out.println("No student found for the id .....:"+studentId);
				} else {
					System.out.println(student);
				}

				break;
				
			case 3:
				 studentId = new Random().nextInt(1000);
				 
				 System.out.println("Enter Student First Name:"); 
				 String studentFirstName = scanner.next();
				 
				 System.out.println("Enter Student Last Name:"); 
				 String studentLastName = scanner.next();
				 
				 System.out.println("Enter Student Email:"); 
				 String studentEmail = scanner.next();
				 
				 student = new Student(studentId, studentFirstName, studentLastName, studentEmail);
				 studentService.createStudent(student);
				 
				 System.out.println("Student has been created:"+student); 
				 
				 break;
				 
			case 4:
				 
				 System.out.println("Enter Student Id to update:"); 
				 studentId = scanner.nextInt();
				 
				 System.out.println("Enter Student First Name:"); 
				 studentFirstName = scanner.next();
				 
				 System.out.println("Enter Student Last Name:"); 
				 studentLastName = scanner.next();
				 
				 System.out.println("Enter Student Email:"); 
				 studentEmail = scanner.next();
				 
				 student = new Student(studentFirstName, studentLastName, studentEmail);
				 studentService.updateStudent(studentId, student);
				 
				 System.out.println("Student has been updated:"+student); 
				 
				 break;

			case 5:
					System.out.println("Enter Student Id to delete :");
					studentId = scanner.nextInt();
					
					studentService.deleteStudentById(studentId);

				break;
				
			case 6:
				
				studentService.deleteAllStudents();
				
				System.out.println("All student have been deleted...");
				
				
			break;
				
			case 0:
				System.out.println("BYE...");
				System.exit(1);
				break;

			default:
				System.out.println("Invalid choice try again ......");
				break;
				
			}
        }while(choice != 0);
        
        } catch (NoSuchBeanDefinitionException noBeanEx) {
			System.err.println("********** NoSuchBeanDefinitionException ********** :"+noBeanEx.getMessage());
		} catch (Exception e) {
			System.err.println("********** invalid value entered ********** ");
			e.printStackTrace();
		} 
    	
    }
}