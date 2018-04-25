package com.zym.bookstore.service.impl;

import com.zym.bookstore.dao.BookDAO;
import com.zym.bookstore.dao.impl.BookDaoImpl;
import com.zym.bookstore.domain.Book;
import com.zym.bookstore.domain.ShoppingCart;
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

}
