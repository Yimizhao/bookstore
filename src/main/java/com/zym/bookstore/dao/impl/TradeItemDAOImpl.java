package com.zym.bookstore.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.zym.bookstore.domain.TradeItem;

public class TradeItemDAOImpl extends BaseDAO<TradeItem> implements com.zym.bookstore.dao.TradeItemDAO {

	@Override
	public void batchSave(Collection<TradeItem> items) {
		String sql = "INSERT INTO tradeitem (bookid, quantity, tradeid) VALUES (?,?,?)";
		List<TradeItem> tradeItems = new ArrayList<TradeItem>(items);
		Object[][] args = null;
		args = new Object[tradeItems.size()][3];
		for (int i = 0; i < args.length; i++) {
			args[i][0] = tradeItems.get(i).getBookId();
			args[i][1] = tradeItems.get(i).getQuantity();
			args[i][2] = tradeItems.get(i).getTradeId();
		}
		batch(sql, args);
	}

	@Override
	public Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId) {
		String sql = "SELECT itemid, bookid, quantity,tradeid FROM tradeitem " + "WHERE tradeid = ?";
		return new LinkedHashSet<TradeItem>(queryForList(sql, tradeId));
	}

}
