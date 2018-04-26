package com.zym.bookstore.dao.impl;

import java.util.HashSet;
import java.util.Set;

import com.zym.bookstore.dao.TradeDAO;
import com.zym.bookstore.domain.Trade;

public class TradeDAOImpl_bak extends BaseDAO<Trade> implements TradeDAO {

	@Override
	public void insert(Trade trade) {
		String sql = "INSERT INTO trade (userid, tradetime) VALUES " + "(?, ?)";
		long tradeId = insert(sql, trade.getUserId(), trade.getTradeTime());
		trade.setTradeId((int) tradeId);
	}

	@Override
	public Set<Trade> getTradesWithUserId(Integer userId) {
		// TODO Auto-generated method stub
		String sql = "SELECT tradeId,tradeTime,userId FROM trade WHERE tradeid = ?";
		return new HashSet<Trade>(queryForList(sql, userId));
	}

}
