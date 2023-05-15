package org.example.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.example.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("orderDao")
public class OrderDaoImpl implements OrderDao {

	private final DataSource dataSource;

	@Autowired
	public OrderDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> list=new ArrayList<Order>();
		try {
			Connection connection=dataSource.getConnection();
			
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery("select * from orders");
			while(resultSet.next())
			{
			 list.add(new Order(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3)));	
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Order createOrder(Order order) throws SQLException {
		Connection connection=dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("insert into orders values(?,?,?)");
		
		preparedStatement.setInt(1, order.getOrderId());
		preparedStatement.setString(2, order.getOrderName());
		preparedStatement.setDouble(3, order.getOrderPrice());
		
		preparedStatement.executeUpdate();
		
		return order;
	}

	@Override
	public Order getOrderById(int orderId) throws SQLException {
		Connection connection=dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from orders where order_Id=?");
		preparedStatement.setInt(1, orderId);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		Order cust = null;
		while(resultSet.next())
		{
		 cust = new Order(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3));
		}
		
		return cust;
	}

	@Override
	public int deleteOrderById(int orderId) throws SQLException {
		Connection connection=dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("delete from orders where order_Id=?");
		preparedStatement.setInt(1, orderId);
		int count = preparedStatement.executeUpdate();
		
		return count;
	}

	@Override
	public Double findMaxOrderValue() throws SQLException {

		Connection connection=dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select max(order_price) from orders");
		ResultSet resultSet = preparedStatement.executeQuery();
		
		Double maxVal=0.00;
		while(resultSet.next())
		{
			maxVal =resultSet.getDouble(1);
		}
		return maxVal;
	}

	@Override
	public Double findMinOrderValue() throws SQLException {
		Connection connection=dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select min(order_price) from orders");
		ResultSet resultSet = preparedStatement.executeQuery();
		
		Double minVal=0.00;
		while(resultSet.next())
		{
			minVal =resultSet.getDouble(1);
		}
		return minVal;
	}

	@Override
	public int deleteAllOrders() throws SQLException {
		Connection connection=dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("delete from orders");

		int count = preparedStatement.executeUpdate();
		
		return count;
	}

	@Override
	public int updateOrderByOrderName(String orderName, String newOrderName) throws SQLException {
		Connection connection=dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("update orders set order_name=? where order_name=?");
		preparedStatement.setString(1, newOrderName);
		preparedStatement.setString(2, orderName);
		
		int count = preparedStatement.executeUpdate();
		
		return count;
	}

	@Override
	public int updateOrderByPrice(Double orderPrice, String newOrderName) throws SQLException {
		Connection connection=dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("update orders set order_name=? where order_price=?");
		preparedStatement.setString(1, newOrderName);
		preparedStatement.setDouble(2, orderPrice);
		
		int count = preparedStatement.executeUpdate();
		
		return count;
	}

}
