package org.example.order.service;

import java.sql.SQLException;
import java.util.List;

import org.example.order.model.Order;

public interface OrderService {


public List<Order> getAllOrders();
	
	public Order getOrderById(int orderId) throws SQLException;

	public Order createOrder(Order order) throws SQLException;

	public Double findMaxOrderValue() throws SQLException;
	
	public Double findMinOrderValue() throws SQLException;

	public int deleteAllOrders() throws SQLException;
	
	public int deleteOrderById(int orderId) throws SQLException;
	
	public int updateOrderByOrderName(String orderName, String newOrderName) throws SQLException;
	
	public int updateOrderByPrice(Double orderPrice, String newOrderName) throws SQLException;

}
