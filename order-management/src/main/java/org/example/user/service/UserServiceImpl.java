package org.example.user.service;

import java.sql.SQLException;

import org.example.user.dao.UserDao;
import org.example.user.model.User;
import org.springframework.stereotype.Component;

@Component("userService")
public class UserServiceImpl implements UserService{

	
	private final UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User findUserById(String userId) throws SQLException {

		User user = userDao.findUserById(userId);
		return user;
	}

}
