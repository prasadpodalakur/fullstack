package org.example.order;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.example.config.SpringConfig;
import org.example.order.model.Order;
import org.example.order.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component("orderApp")
public class OrderApp {
	static Scanner scanner = new Scanner(System.in);
	
	public void processOrder() {

    	try {

			ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
			OrderService orderService = context.getBean("orderService", OrderService.class);
			
			int choice =0;
			do {
				System.out.println("1. getAllOrders");
				System.out.println("2. getOrderById");
				System.out.println("3. createOrder");
				System.out.println("4. findMaxOrderValue");
				System.out.println("5. findMinOrderValue");
				System.out.println("6. deleteAllOrders");
				System.out.println("7. deleteOrderById");
				System.out.println("8. updateOrderByOrderName");
				System.out.println("9. updateOrderByPrice");
				
				System.out.println("Enter option:");
				choice = scanner.nextInt();
				
				switch (choice) {
				case 1:
					List<Order> list = orderService.getAllOrders();
					if(list.isEmpty()) {
						System.out.println("No orders found .....");
					}
					for (Order c : list) {
						System.out.println(c);
					}
					break;
					
				case 2:
					
					System.out.println("Enter Order Id :");
					int orderId = scanner.nextInt();
					
					Order order = orderService.getOrderById(orderId);
					if(order == null) {
						System.out.println("No order found for the given Id :"+orderId);
					}else {
						System.out.println(order);
					}
					
					break;
					
				case 3:
					 orderId = new Random().nextInt(1000);
					 
					 System.out.println("Enter Order Name:"); 
					 String orderName = scanner.next();
					 
					 System.out.println("Enter Order Price:"); 
					 Double orderPrice = scanner.nextDouble();
					 
					 order = new Order(orderId, orderName, orderPrice);
					 orderService.createOrder(order);
					 System.out.println("Order has been created successfully :"+order);
					 break;
				
				case 4:
					Double maxValue = orderService.findMaxOrderValue();
					System.out.println("Max Order Value :"+maxValue);
				 
					break;	
					
				case 5:
					Double minValue = orderService.findMinOrderValue();
					System.out.println("Min Order Value :"+minValue);
				 
					break;	
					
				case 6:
					int count = orderService.deleteAllOrders();
					if(count <= 0) {
						System.out.println("No orders found for the deletion...");
					}else {
						System.out.println("All the orders have been deleted successfully....");
					}
						
					break;
					
				case 7:
					
					try {
						
						System.out.println("Enter Order Id to delete :");
						orderId = scanner.nextInt();
						
						count = orderService.deleteOrderById(orderId);
						if(count <= 0) {
							System.out.println("No order found for the given Id :"+orderId);
						}else {
							System.out.println("order has been deleted successfully for the given Id:"+orderId);
						}
						
					}catch(InputMismatchException e) {
						System.err.println("Invalid input..");
					}
					break;
					
				case 8:
					
					try {
						System.out.println("Enter Order Name to update :");
						orderName = scanner.next();
						
						System.out.println("Enter New Order Name to update :");
						String newOrderName = scanner.next();
						
						count = orderService.updateOrderByOrderName(orderName, newOrderName);
						if(count <= 0) {
							System.out.println("No order found for the given name :"+orderName);
						}else {
							System.out.println("order has been updated successfully for the given order name :"+orderName);
						}
						
					}catch(InputMismatchException e) {
						System.err.println("Invalid input..");
					}
					break;
					
				case 9:
					
					try {
						System.out.println("Enter Order Price to update :");
						orderPrice = scanner.nextDouble();
						
						System.out.println("Enter New Order Name to update :");
						String newOrderName = scanner.next();
						
						count = orderService.updateOrderByPrice(orderPrice, newOrderName);
						if(count <= 0) {
							System.out.println("No order found for the given price :"+orderPrice);
						}else {
							System.out.println("order has been updated successfully for the given order price:"+orderPrice);
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
