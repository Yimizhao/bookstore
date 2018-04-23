package com.zym.bookstore.dao.impl;


import org.junit.Test;

import com.zym.bookstore.dao.AccountDAO;

public class TestAccountDAOImpl {
	private AccountDAO accountDAO = new AccountDAOImpl();

	@Test
	public void testGet() {

		System.out.println(accountDAO.get(1));
	}

	@Test
	public void testUpdateBalance() {
		accountDAO.updateBalance(1, 10);
	}

}
