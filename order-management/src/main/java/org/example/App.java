package org.example;

import java.util.Scanner;

import org.example.config.SpringConfig;
import org.example.order.OrderApp;
import org.example.user.UserApp;
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
    	try {
    		
    		System.out.println("Welcome to Order management......");
    		
			ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

			UserApp userApp = context.getBean("userApp", UserApp.class);
			
			if(userApp.isUserAuthenticated()) {
				OrderApp orderApp = context.getBean("orderApp", OrderApp.class);
				orderApp.processOrder();
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
}
