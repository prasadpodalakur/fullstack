package org.example.demo;

import java.util.List;

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
public class App 
{
    public static void main( String[] args )
    {
        try {
        	
        	ApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class);
			
        	CustomerService customerService =context.getBean("customerService",CustomerService.class);
        	
        	List<Customer> list=customerService.getAllCustomers();
        	for(Customer c:list)
        	{
        	  System.out.println(c);	
        	}
        	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
}
