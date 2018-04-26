package com.zym.bookstore.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import com.zym.bookstore.dao.AccountDAO;
import com.zym.bookstore.dao.BookDAO;
import com.zym.bookstore.dao.TradeDAO;
import com.zym.bookstore.dao.TradeItemDAO;
import com.zym.bookstore.dao.UserDAO;
import com.zym.bookstore.dao.impl.AccountDAOImpl;
import com.zym.bookstore.dao.impl.BookDaoImpl;
import com.zym.bookstore.dao.impl.TradeDAOImpl;
import com.zym.bookstore.dao.impl.TradeItemDAOImpl;
import com.zym.bookstore.dao.impl.UserDAOImpl;
import com.zym.bookstore.domain.Book;
import com.zym.bookstore.domain.ShoppingCart;
import com.zym.bookstore.domain.ShoppingCartItem;
import com.zym.bookstore.domain.Trade;
import com.zym.bookstore.domain.TradeItem;
import com.zym.bookstore.domain.User;
import com.zym.bookstore.service.BookService;
import com.zym.bookstore.web.CriteriaBook;
import com.zym.bookstore.web.Page;

public class BookServiceImpl implements BookService {
	private BookDAO bookDAO = new BookDaoImpl();

	@Override
	public Page<Book> getPage(CriteriaBook criteriaBook) {
		return bookDAO.getPage(criteriaBook);
	}

	@Override
	public Book getBook(int bookId) {
		return bookDAO.getBook(bookId);
	}

	@Override
	public boolean addToCart(int bookId, ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub
		Book book = bookDAO.getBook(bookId);
		if (book == null) {

			return Boolean.FALSE;
		}
		shoppingCart.addBook(book);
		return Boolean.TRUE;
	}

	@Override
	public void removeShoppingCartItemFromShoppingCart(ShoppingCart shoppingCart, int id) {
		shoppingCart.removeItem(id);

	}

	@Override
	public void clearShoppingCart(ShoppingCart shoppingCart) {
		shoppingCart.clear();
	}

	@Override
	public void updateItemQuantity(ShoppingCart sc, int id, int quantity) {
		sc.updateItemQuantity(id, quantity);
	}

	private UserDAO userDAO = new UserDAOImpl();
	private TradeDAO tradeDAO = new TradeDAOImpl();
	private AccountDAO accountDAO = new AccountDAOImpl();
	private TradeItemDAO tradeItemDAO = new TradeItemDAOImpl();

	@Override
	public void cash(ShoppingCart shoppingCart, String username, String accountId) {
		// 取得用户信息
		User user = userDAO.getUser(username);
		// 用户Id
		Integer userId = user.getUserId();
		// 支付金额
		float totalMoney = shoppingCart.getTotalMoney();

		// 更新商品库存
		bookDAO.batchUpdateStoreNumberAndSalesAmount(shoppingCart.getItems());

		// 更新账余额
		accountDAO.updateBalance(Integer.parseInt(accountId), totalMoney);
		
//		int e = 10 / 0;

		// 更新一次交易记录
		Trade trade = new Trade();
		trade.setUserId(userId);
		trade.setTradeTime(new java.sql.Date(new Date().getTime()));
		tradeDAO.insert(trade);
		// 取得Trade的Id
		Integer tradeId = trade.getTradeId();
		Collection<TradeItem> items = new HashSet<TradeItem>();
		TradeItem tradeItem;

		Iterator<ShoppingCartItem> iterator = shoppingCart.getItems().iterator();
		while (iterator.hasNext()) {
			tradeItem = new TradeItem();
			ShoppingCartItem shoppingCartItem = (ShoppingCartItem) iterator.next();
			Book book = shoppingCartItem.getBook();
			tradeItem.setBookId(book.getId());
			tradeItem.setQuantity(shoppingCartItem.getQuantity());
			tradeItem.setTradeId(tradeId);
			items.add(tradeItem);
		}
		// 更新交易项
		tradeItemDAO.batchSave(items);
	}
}
