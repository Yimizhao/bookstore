package com.zym.bookstore.service;

import com.zym.bookstore.domain.Book;
import com.zym.bookstore.domain.ShoppingCart;
import com.zym.bookstore.web.CriteriaBook;
import com.zym.bookstore.web.Page;

public interface BookService {
	public Page<Book> getPage(CriteriaBook criteriaBook);

	public Book getBook(int bookId);

	public boolean addToCart(int bookId, ShoppingCart shoppingCart);

	public void removeShoppingCartItemFromShoppingCart(ShoppingCart shoppingCart, int id);

	public void clearShoppingCart(ShoppingCart shoppingCart);

	public void updateItemQuantity(ShoppingCart sc, int id, int quantity);

	public void cash(ShoppingCart shoppingCart, String username, String accountId);

}
