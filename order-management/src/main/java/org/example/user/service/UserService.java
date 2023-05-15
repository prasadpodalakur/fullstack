package org.example.user.service;

import java.sql.SQLException;

import org.example.user.model.User;

public interface UserService {
	
	
	public User findUserById(String userId) throws SQLException;
	
}
