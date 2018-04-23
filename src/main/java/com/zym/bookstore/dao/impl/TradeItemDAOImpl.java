package com.zym.bookstore.dao.impl;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import com.zym.bookstore.domain.TradeItem;

public class TradeItemDAOImpl extends BaseDAO<TradeItem> implements com.zym.bookstore.dao.TradeItemDAO {

	@Override
	public void batchSave(Collection<TradeItem> items) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId) {
		String sql = "SELECT itemid, bookid, quantity,tradeid FROM tradeitem " + "WHERE tradeid = ?";
		return new LinkedHashSet<TradeItem>(queryForList(sql, tradeId));
	}

}
