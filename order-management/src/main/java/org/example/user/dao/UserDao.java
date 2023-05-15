package org.example.user.dao;

import java.sql.SQLException;

import org.example.user.model.User;

public interface UserDao {
	
	public User findUserById(String userId) throws SQLException;

}
