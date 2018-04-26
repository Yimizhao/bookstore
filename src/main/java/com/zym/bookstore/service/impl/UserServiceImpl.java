package com.zym.bookstore.service.impl;

import com.zym.bookstore.dao.UserDAO;
import com.zym.bookstore.dao.impl.UserDAOImpl;
import com.zym.bookstore.domain.User;
import com.zym.bookstore.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO = new UserDAOImpl();

	@Override
	public User getUserByName(String username) {
		return userDAO.getUser(username);
	}

}
