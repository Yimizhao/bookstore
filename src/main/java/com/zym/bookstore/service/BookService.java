package com.zym.bookstore.service;

import com.zym.bookstore.domain.Book;
import com.zym.bookstore.domain.ShoppingCart;
import com.zym.bookstore.web.CriteriaBook;
import com.zym.bookstore.web.Page;

public interface BookService {
	public Page<Book> getPage(CriteriaBook criteriaBook);

	public Book getBook(int bookId);

	public boolean addToCart(int bookId, ShoppingCart shoppingCart);

}
