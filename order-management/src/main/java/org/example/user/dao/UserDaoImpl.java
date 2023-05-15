package org.example.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.example.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userDao")
public class UserDaoImpl implements UserDao {

	private final DataSource dataSource;

	@Autowired
	public UserDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	@Override
	public User findUserById(String userId) throws SQLException {
		Connection connection=dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from users where user_Id=?");
		preparedStatement.setString(1, userId);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		User user = null;
		while(resultSet.next())
		{
			user = new User(resultSet.getString(1), resultSet.getString(2));
		}
		
		return user;
	}
 
}
