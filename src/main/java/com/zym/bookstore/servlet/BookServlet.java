package com.zym.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zym.bookstore.domain.Book;
import com.zym.bookstore.domain.ShoppingCart;
import com.zym.bookstore.service.BookService;
import com.zym.bookstore.service.impl.BookServiceImpl;
import com.zym.bookstore.web.BookStoreWebUtils;
import com.zym.bookstore.web.CriteriaBook;
import com.zym.bookstore.web.Page;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/bookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void getBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageNoStr = request.getParameter("pageNo");
		String minPriceStr = request.getParameter("minPrice");
		String maxPriceStr = request.getParameter("maxPrice");

		int pageNo = 0;
		float minPrice = Float.MIN_VALUE;
		float maxPrice = Float.MAX_VALUE;
		HttpSession session = request.getSession();
		session.setAttribute("minPrice", minPriceStr);
		session.setAttribute("maxPrice", maxPriceStr);

		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			// throw new NumberFormatException("数值转换异常");
		}
		try {
			// minPrice = Float.parseFloat(minPriceStr);
			minPrice = Integer.parseInt(minPriceStr);
		} catch (NumberFormatException e) {
		}
		try {
			// maxPrice = Float.parseFloat(maxPriceStr);
			maxPrice = Integer.parseInt(maxPriceStr);
		} catch (NumberFormatException e) {
		}

		CriteriaBook criteriaBook = new CriteriaBook(minPrice, maxPrice, pageNo);

		Page<Book> bookPage = bookService.getPage(criteriaBook);

		request.setAttribute("bookPage", bookPage);

		request.getRequestDispatcher("page/books.jsp").forward(request, response);

	}

	protected void getBookInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		int id = -1;
		Book book = null;
		try {
			id = Integer.parseInt(bookId);
		} catch (NumberFormatException e) {
		}
		if (id != -1) {
			book = bookService.getBook(id);
		}
		if (book == null) {

			response.sendRedirect(request.getContextPath() + "/error/error_1.html");
			return;
		}
		request.setAttribute("book", book);
		request.getRequestDispatcher("page/book.jsp").forward(request, response);
	}

	protected void addToCart(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 1、取得商品Id
		String bookId = request.getParameter("bookId");
		int id = -1;
		boolean flag = Boolean.FALSE;
		try {
			id = Integer.parseInt(bookId);
		} catch (NumberFormatException e) {
		}

		if (id > 0) {
			// 2、取得购物车
			ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(request);
			// 3、添加到购物车
			flag = bookService.addToCart(id, shoppingCart);
		}

		if (!flag) {
			response.sendRedirect(request.getContextPath() + "/error/error_1.html");
			return;
		}

		// 4、返回books页面
		this.getBooks(request, response);
	}

}
