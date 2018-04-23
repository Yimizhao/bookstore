package com.zym.bookstore.dao.impl;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import com.zym.bookstore.dao.TradeDAO;
import com.zym.bookstore.domain.Trade;

public class TestTradeDAOImpl {
	private TradeDAO tradeDao = new TradeDAOImpl();

	@SuppressWarnings("deprecation")
	@Test
	public void testInsertTrade() {
		Trade trade = new Trade();
		trade.setTradeId(2);
		trade.setUserId(1);
		trade.setTradeTime(new Date(2015, 6, 30));
		tradeDao.insert(trade);
	}

	@Test
	public void testGetTradesWithUserId() {
		System.out.println(tradeDao.getTradesWithUserId(1));
		
	}

}
