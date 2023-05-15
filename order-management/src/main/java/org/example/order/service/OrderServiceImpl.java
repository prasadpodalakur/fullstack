package org.example.order.service;

import java.sql.SQLException;
import java.util.List;

import org.example.order.dao.OrderDao;
import org.example.order.model.Order;
import org.springframework.stereotype.Component;

@Component("orderService")
public class OrderServiceImpl implements OrderService{

	
	private final OrderDao orderDao;

	public OrderServiceImpl(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public List<Order> getAllOrders() {

		return orderDao.getAllOrders();
	}


	@Override
	public Order createOrder(Order order) throws SQLException {

		orderDao.createOrder(order);
		
		return order;
		
	}


	@Override
	public Order getOrderById(int orderId) throws SQLException {

		Order order = orderDao.getOrderById(orderId);
		return order;
	}

	@Override
	public int deleteOrderById(int orderId) throws SQLException {
		 
		return orderDao.deleteOrderById(orderId);
		
	}

	@Override
	public Double findMaxOrderValue() throws SQLException {

		return orderDao.findMaxOrderValue();
	}

	@Override
	public Double findMinOrderValue() throws SQLException {

		return orderDao.findMinOrderValue();
	}

	@Override
	public int deleteAllOrders() throws SQLException {
		
		return orderDao.deleteAllOrders();
	}

	@Override
	public int updateOrderByOrderName(String orderName, String newOrderName) throws SQLException {

		return orderDao.updateOrderByOrderName(orderName, newOrderName);
	}

	@Override
	public int updateOrderByPrice(Double orderPrice, String newOrderName) throws SQLException {

		return orderDao.updateOrderByPrice(orderPrice, newOrderName);
	}

	
}
