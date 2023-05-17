package org.example.todo;

import java.util.Random;
import java.util.Scanner;

import org.example.todo.model.ToDo;
import org.example.todo.service.ToDoServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
	static Scanner scanner = new Scanner(System.in);
    public static void main( String[] args )
    {
        System.out.println( "Hello ToDo World!" );
        
        int choice=0;
        try {
        do {
        	ToDoServiceImpl toDoService = new ToDoServiceImpl();
        	
        	System.out.println("1. getAllToDos");
			System.out.println("2. getToDoById");
			System.out.println("3. createToDo");
			System.out.println("4. updateToDo");
			System.out.println("5. deleteToDoById");
			System.out.println("6. deleteAllToDos");
        	
			System.out.println("Enter option:");
			choice = scanner.nextInt();
			System.out.println("Enter option:"+choice);
			
			switch (choice) {
			case 1:
				java.util.List<ToDo> toDosList = toDoService.getAllToDos();
				
				if(toDosList.isEmpty()) {
					System.out.println("No ToDos found .....");
				}
		        for(ToDo s:toDosList)
		        {
		        	System.out.println(s);
		        }
				break;
				
			case 2:
				
				System.out.println("Enter ToDo Id :");
				int toDoId = scanner.nextInt();
				
				ToDo toDo = toDoService.getToDoById(toDoId);
				if(toDo == null) {
					System.out.println("No ToDo found for the id .....:"+toDoId);
				} else {
					System.out.println(toDo);
				}

				break;
				
			case 3:
				 toDoId = new Random().nextInt(1000);
				 
				 System.out.println("Enter ToDo Name:"); 
				 String toDoName = scanner.next();
				 
				 System.out.println("Enter ToDo iscompleted:"); 
				 Boolean iscompleted = scanner.nextBoolean();
				 
				 toDo = new ToDo(toDoId, toDoName, iscompleted);
				 toDoService.createToDo(toDo);
				 
				 System.out.println("ToDo has been created:"+toDo); 
				 
				 break;
				 
			case 4:
				 
				 System.out.println("Enter ToDo Id to update:"); 
				 toDoId = scanner.nextInt();
				 
				 System.out.println("Enter ToDo Name:"); 
				 toDoName = scanner.next();
				 
				 System.out.println("Enter ToDo iscompleted:"); 
				 iscompleted = scanner.nextBoolean();
				 
				 toDo = new ToDo(toDoName, iscompleted);
				 toDoService.updateToDo(toDoId, toDo);
				 
				 System.out.println("ToDo has been updated:"+toDo); 
				 
				 break;

			case 5:
					System.out.println("Enter ToDo Id to delete :");
					
					toDoId = scanner.nextInt();
					
					toDoService.deleteToDoById(toDoId);

				break;
				
			case 6:
				
				toDoService.deleteAllToDos();
				
				System.out.println("All ToDo have been deleted...");
				
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
        
        } catch (IllegalArgumentException ex) {
			System.err.println("********** Exception occurred **********: "+ex.getMessage());
		}catch (Exception e) {
			System.err.println("********** invalid value entered ********** ");
		} 
    }
}
