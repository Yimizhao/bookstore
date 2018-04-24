package com.zym.bookstore.domain;

/**
 * 封装了购物车中的商品, 包含对商品的引用以及购物车中该商品的数量
 *
 */
public class ShoppingCartItem_bak {
	private Book book;
	private int quantity;

	ShoppingCartItem_bak(Book book) {
		this.book = book;

		// 初始化书籍数量
		this.quantity = 1;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Book getBook() {
		return book;
	}

	public float getItemMoney() {
		return this.quantity * book.getPrice();
	}

	public void increment() {
		this.quantity++;
	}
}
