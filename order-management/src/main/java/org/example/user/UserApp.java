package org.example.user;

import java.util.Scanner;

import org.example.config.SpringConfig;
import org.example.user.model.User;
import org.example.user.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component("userApp")
public class UserApp {

	static Scanner scanner = new Scanner(System.in);
	
    public boolean isUserAuthenticated()
    {
    	boolean authenticated = false;
    	
    	try {

			ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
			UserService userService = context.getBean("userService", UserService.class);
			
			int counter = 0;
			do {
				
				System.out.println("Enter user Id:");
				String userId = scanner.next();
				
				System.out.println("Enter Password:");
				String password = scanner.next();
				
				User user = userService.findUserById(userId);
				if(user == null) {
					System.out.println("Invalid user Id ... :"+ userId);
					++counter;
				}else {
					
					if(password.equals(user.getPassword())) {
						System.out.println("Successfully Authenticated.....");
						
						authenticated = true;
						break;
					}else {
						System.out.println("Invalid password ... :");
						
						++counter;
					}
				}
				
				if(counter == 3) {
					System.out.println("Exceeded maximum number of attempts ... Please try after some time ....");
				}
				 
			}while (counter < 3);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return authenticated;
    }

}
