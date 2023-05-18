package org.example.demo;

import java.util.Random;
import java.util.Scanner;

import org.example.demo.config.SpringConfig;
import org.example.demo.instructor.model.Instructor;
import org.example.demo.instructor.service.InstructorServiceImpl;
import org.example.demo.student.model.Student;
import org.example.demo.student.service.StudentServiceImpl;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mysql.cj.Constants;

/**
 * Hello world!
 *
 */
public class App 
{
	static Scanner scanner = new Scanner(System.in);
	
    public static void main( String[] args )
    {
    	System.out.println( "Hello Student/Instructor World!" );
    	
    	int choice=0;

        try {
        do {
        	
        	System.out.println("1. processStudent");
			System.out.println("2. processInstructor");


			System.out.println("Enter option:");
			choice = scanner.nextInt();
			
			
			switch (choice) {
			case 1:
				processStudent();
				break;
				
			case 2:
				
				processInstructor();

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


	private static void processStudent() {
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
				 
				 student = new Student( studentFirstName, studentLastName, studentEmail);
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
	
	private static void processInstructor() {

		int choice=0;
        try {
        do {
        	
        	ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        	InstructorServiceImpl instructorService = context.getBean("instructorService",InstructorServiceImpl.class);
        		
        	System.out.println("1. getAllInstructors");
			System.out.println("2. getInstructorById");
			System.out.println("3. createInstructor");
			System.out.println("4. updateInstructor");
			System.out.println("5. deleteInstructorById");
			System.out.println("6. deleteAllInstructors");
        	

			System.out.println("Enter option:");
			choice = scanner.nextInt();
			
			
			switch (choice) {
			case 1:
				java.util.List<Instructor> instructorsList = instructorService.getAllInstructors();
				
				if(instructorsList.isEmpty()) {
					System.out.println("No instructors found .....");
				}
		        for(Instructor s:instructorsList)
		        {
		        	System.out.println(s);
		        }
				break;
				
			case 2:
				
				System.out.println("Enter Instructor Id :");
				int instructorId = scanner.nextInt();
				
				Instructor instructor = instructorService.getInstructorById(instructorId);
				if(instructor == null) {
					System.out.println("No instructor found for the id .....:"+instructorId);
				} else {
					System.out.println(instructor);
				}

				break;
				
			case 3:
				 
				 System.out.println("Enter Instructor Name:"); 
				 String instructorName = scanner.next();
				 
				 System.out.println("Enter instructor Rating:"); 
				 String instructorRating = scanner.next();
				 
				 instructor = new Instructor( instructorName, instructorRating);
				 instructorService.createInstructor(instructor);
				 
				 System.out.println("Instructor has been created:"+instructor); 
				 
				 break;
				 
			case 4:
				 
				 System.out.println("Enter Instructor Id to update:"); 
				 instructorId = scanner.nextInt();
				 
				 instructorName="";
				 instructorRating = "";
				 
				 System.out.println("do you want to update Instructor Name ?"); 
				 String optionUpdate = scanner.next();
				 if(optionUpdate.equalsIgnoreCase("YES")) {
					 System.out.println("Enter Instructor Name:"); 
					 instructorName = scanner.next();
				 }
				 
				 System.out.println("do you want to update Instructor Rating ?"); 
				 optionUpdate = scanner.next();
				 if(optionUpdate.equalsIgnoreCase("YES")) {
					 System.out.println("Enter instructor Rating:"); 
					 instructorRating = scanner.next();
				 }
				 
				 instructor = new Instructor(instructorName, instructorRating);
				 instructorService.updateInstructor(instructorId, instructor);
				 
				 System.out.println("Instructor has been updated:"+instructor); 
				 
				 break;

			case 5:
					System.out.println("Enter Instructor Id to delete :");
					instructorId = scanner.nextInt();
					
					instructorService.deleteInstructorById(instructorId);

				break;
				
			case 6:
				
				instructorService.deleteAllInstructors();
				
				System.out.println("All instructor have been deleted...");
				
				
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