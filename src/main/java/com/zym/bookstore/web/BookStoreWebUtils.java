package com.zym.bookstore.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zym.bookstore.domain.ShoppingCart;

public class BookStoreWebUtils {

	public static ShoppingCart getShoppingCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ShoppingCart shoppingCart;
		if (session.getAttribute("shoppingCart") == null) {
			shoppingCart = new ShoppingCart();
			session.setAttribute("shoppingCart", shoppingCart);
		} else {
			shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		}
		return shoppingCart;
	}
}
