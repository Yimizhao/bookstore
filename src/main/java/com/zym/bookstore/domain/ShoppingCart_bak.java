package com.zym.bookstore.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShoppingCart_bak {
	private Map<Integer, ShoppingCartItem_bak> books = new HashMap<>();

	public void add(Book book) {
		Integer bookId = book.getId();
		if (this.hasBook(bookId)) {
			books.get(bookId).increment();
		} else {
			ShoppingCartItem_bak shoppingCartItem_bak = new ShoppingCartItem_bak(book);
			books.put(bookId, shoppingCartItem_bak);
		}
	}

	/**
	 * 检验购物车中是否有该书
	 * 
	 * @param bookId
	 *            书的Id
	 * @return 有返回true,否则返回false
	 */
	public boolean hasBook(Integer bookId) {
		// if (books.keySet().contains(bookId))
		// return true;
		// return false;
		return books.containsKey(bookId);
	}

	public Map<Integer, ShoppingCartItem_bak> getBooks() {
		return books;
	}

	public Collection<ShoppingCartItem_bak> getItems() {
		return books.values();
	}

	public int getBookNumber() {
		int bookNumber = 0;
		Collection<ShoppingCartItem_bak> shoppingCartItem_baks = getItems();
		for (Iterator<ShoppingCartItem_bak> iterator = shoppingCartItem_baks.iterator(); iterator.hasNext();) {
			ShoppingCartItem_bak shoppingCartItem_bak = (ShoppingCartItem_bak) iterator.next();
			bookNumber += shoppingCartItem_bak.getQuantity();
		}

		return bookNumber;
	}

	public float getToltalMoney() {
		float toltalMoney = 0f;
		Collection<ShoppingCartItem_bak> shoppingCartItem_baks = getItems();
		for (Iterator<ShoppingCartItem_bak> iterator = shoppingCartItem_baks.iterator(); iterator.hasNext();) {
			ShoppingCartItem_bak shoppingCartItem_bak = (ShoppingCartItem_bak) iterator.next();
			toltalMoney += shoppingCartItem_bak.getItemMoney();
		}
		return toltalMoney;
	}

	public boolean isEmpty() {

		return books.isEmpty();
	}

	public void clear() {
		books.clear();
	}

	public void removeItem(int bookId) {
		if (hasBook(bookId)) {
			books.remove(bookId);
		}
	}

	public void updateItemQuantity(int bookId, int quantity) {
		if (hasBook(bookId)) {
			books.get(bookId).setQuantity(quantity);
		}
	}
}
