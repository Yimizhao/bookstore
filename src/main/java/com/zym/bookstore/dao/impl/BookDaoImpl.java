package com.zym.bookstore.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.zym.bookstore.dao.BookDAO;
import com.zym.bookstore.domain.Book;
import com.zym.bookstore.domain.ShoppingCartItem;
import com.zym.bookstore.web.CriteriaBook;
import com.zym.bookstore.web.Page;

public class BookDaoImpl extends BaseDAO<Book> implements BookDAO {

	@Override
	public Book getBook(int id) {
		String sql = "SELECT * FROM mybooks WHERE id = ?";
		return query(sql, id);
	}

	@Override
	public Page<Book> getPage(CriteriaBook cb) {
		Page<Book> page = new Page<>(cb.getPageNo());
		page.setTotalItemNumber(getTotalBookNumber(cb));
		// 校验PageNo的合法性(位置很重要)
		cb.setPageNo(page.getPageNo());
		page.setList(getBookList(cb, page.getPageSize()));
		return page;
	}

	@Override
	public long getTotalBookNumber(CriteriaBook cb) {

		String sql = "SELECT COUNT(id) FROM mybooks WHERE Price >= ? AND Price <= ?";
		return getSingleVal(sql, cb.getMinPrice(), cb.getMaxPrice());
	}

	@Override
	public List<Book> getBookList(CriteriaBook cb, int pageSize) {
		String sql = "SELECT id,Author,title,Price,Publishingdate,Salesamount,Storenumber,Remark FROM mybooks WHERE Price >= ? AND Price <= ? limit ?,?";
		return queryForList(sql, cb.getMinPrice(), cb.getMaxPrice(), (cb.getPageNo() - 1) * pageSize, pageSize);
	}

	@Override
	public int getStoreNumber(Integer id) {
		String sql = "SELECT Storenumber FROM mybooks WHERE id >= ?";
		return getSingleVal(sql, id);
	}

	@Override
	public void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items) {
		String sql = "UPDATE mybooks SET Salesamount = Salesamount + ? , Storenumber = Storenumber - ? WHERE Id = ?";
		List<ShoppingCartItem> shoppingCartItems = new ArrayList<>(items);
		Object[][] args = new String[items.size()][3];
		for (int i = 0; i < shoppingCartItems.size(); i++) {
			args[i][0] = shoppingCartItems.get(i).getQuantity();
			args[i][1] = shoppingCartItems.get(i).getQuantity();
			args[i][2] = shoppingCartItems.get(i).getBook().getId();
		}
		batch(sql, args);
	}

}
