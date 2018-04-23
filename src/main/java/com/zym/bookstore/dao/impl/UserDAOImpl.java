package com.zym.bookstore.dao.impl;

import com.zym.bookstore.dao.UserDAO;
import com.zym.bookstore.domain.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

	@Override
	public User getUser(String username) {
		String sql = "SELECT userid,username,accountid FROM userinfo WHERE username = ?";
		return query(sql, username);
	}

}
