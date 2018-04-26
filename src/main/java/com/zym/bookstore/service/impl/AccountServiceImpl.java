package com.zym.bookstore.service.impl;

import com.zym.bookstore.dao.AccountDAO;
import com.zym.bookstore.dao.impl.AccountDAOImpl;
import com.zym.bookstore.domain.Account;
import com.zym.bookstore.service.AccountService;

public class AccountServiceImpl implements AccountService {
	private AccountDAO accountDAO = new AccountDAOImpl();


	@Override
	public Account getAccount(int accountid) {
		return accountDAO.get(accountid);
	}

}
