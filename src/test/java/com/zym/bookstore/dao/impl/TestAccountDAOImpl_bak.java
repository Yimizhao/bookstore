package com.zym.bookstore.dao.impl;


import org.junit.Test;

import com.zym.bookstore.dao.AccountDAO;
import com.zym.bookstore.domain.Account;

public class TestAccountDAOImpl_bak {

	private AccountDAO accountDao = new AccountDAOImpl_bak();

	@Test
	public void testGet() {
		Account account;
		account = accountDao.get(1);
		System.out.println(account);
	}

	@Test
	public void testUpdateBalance() {
	}

}
