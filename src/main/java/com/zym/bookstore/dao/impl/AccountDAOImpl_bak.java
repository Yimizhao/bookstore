package com.zym.bookstore.dao.impl;

import com.zym.bookstore.dao.AccountDAO;
import com.zym.bookstore.domain.Account;

public class AccountDAOImpl_bak extends BaseDAO<Account> implements AccountDAO {

	@Override
	public Account get(Integer accountId) {
		String sql = "SELECT accountId,balance FROM account WHERE accountid = ?";
		return query(sql, accountId);
	}

	@Override
	public void updateBalance(Integer accountId, float amount) {
		String sql = "UPDATE account SET balance = balance - ? WHERE accountId = ?";
		update(sql, amount, accountId);

	}

}
