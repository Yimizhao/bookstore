package com.zym.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.google.gson.Gson;
import com.zym.bookstore.domain.Account;
import com.zym.bookstore.domain.Book;
import com.zym.bookstore.domain.ShoppingCart;
import com.zym.bookstore.domain.ShoppingCartItem;
import com.zym.bookstore.domain.User;
import com.zym.bookstore.service.AccountService;
import com.zym.bookstore.service.BookService;
import com.zym.bookstore.service.UserService;
import com.zym.bookstore.service.impl.AccountServiceImpl;
import com.zym.bookstore.service.impl.BookServiceImpl;
import com.zym.bookstore.service.impl.UserServiceImpl;
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
			throw new RuntimeException(e);
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

	protected void toCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("page/cart.jsp").forward(request, response);
	}

	protected void remove(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String bookId = request.getParameter("bookId");
		int id = -1;
		try {
			id = Integer.parseInt(bookId);
		} catch (NumberFormatException e) {
		}

		if (id < 0) {
			response.sendRedirect(request.getContextPath() + "/error/error_1.html");
			return;
		}
		ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(request);
		bookService.removeShoppingCartItemFromShoppingCart(shoppingCart, id);

		request.getRequestDispatcher("page/cart.jsp").forward(request, response);
	}

	protected void clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(request);
		bookService.clearShoppingCart(shoppingCart);
		request.getRequestDispatcher("page/emptycart.jsp").forward(request, response);
	}

	protected void updateItemQuantity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 4. 在 updateItemQuantity 方法中, 获取 quanity, id, 再获取购物车对象, 调用 service 的方法做修改
		String idStr = request.getParameter("id");
		String quantityStr = request.getParameter("quantity");

		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);

		int id = -1;
		int quantity = -1;

		try {
			id = Integer.parseInt(idStr);
			quantity = Integer.parseInt(quantityStr);
		} catch (Exception e) {
		}

		if (id > 0 && quantity > 0)
			bookService.updateItemQuantity(sc, id, quantity);

		// 5. 传回 JSON 数据: bookNumber:xx, totalMoney
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("bookNumber", sc.getBookNumber());
		result.put("totalMoney", sc.getTotalMoney());

		Gson gson = new Gson();
		String jsonStr = gson.toJson(result);
		response.setContentType("text/javascript");
		response.getWriter().print(jsonStr);
	}

	protected void forwardPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		request.getRequestDispatcher("page/" + page + ".jsp").forward(request, response);
	}

	protected void cash(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 简单验证: 验证表单域的值是否符合基本的规范: 是否为空, 是否可以转为 int 类型, 是否是一个 email. 不需要进行查询
		// 数据库或调用任何的业务方法.
		String username = StringUtils.trim(request.getParameter("creditCardName"));
		String accountId = StringUtils.trim(request.getParameter("creditCardNumber"));

		StringBuffer errors = validateFormField(username, accountId);

		// 表单验证通过。
		if (StringUtils.equals(StringUtils.EMPTY, errors.toString())) {
			errors = validateUser(username, accountId);

			// 用户名和账号验证通过
			if (StringUtils.equals(StringUtils.EMPTY, errors.toString())) {
				errors = validateBookStoreNumber(request);

				// 库存验证通过
				if (StringUtils.equals(StringUtils.EMPTY, errors.toString())) {
					errors = validateBalance(request, accountId);
				}
			}
		}

		if (!StringUtils.equals(StringUtils.EMPTY, errors.toString())) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("page/cash.jsp").forward(request, response);
			return;
		}

		request.removeAttribute("errors");
		// 验证通过执行具体的逻辑操作
		bookService.cash(BookStoreWebUtils.getShoppingCart(request), username, accountId);
		response.sendRedirect(request.getContextPath() + "/page/success.jsp");
	}

	private AccountService accountService = new AccountServiceImpl();

	/**
	 * 账户金额校验
	 * 
	 * @param request
	 *            页面请求
	 * @param accountId
	 *            账户ID
	 * @return 校验结果信息
	 */
	private StringBuffer validateBalance(HttpServletRequest request, String accountId) {
		StringBuffer errors = new StringBuffer(StringUtils.EMPTY);
		// 购物车
		ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(request);
		// 购物车所需支付金额
		float totalMoney = shoppingCart.getTotalMoney();
		// 账户拥有金额
		Account account = accountService.getAccount(Integer.parseInt(accountId));
		float balance = account.getBalance();
		if (Float.compare(totalMoney, balance) > 0) {
			errors.append("账户余额不足");
		}
		return errors;
	}

	/**
	 * 商品库存校验
	 * 
	 * @param request
	 * @return 校验结果信息
	 */
	private StringBuffer validateBookStoreNumber(HttpServletRequest request) {
		StringBuffer errors = new StringBuffer(StringUtils.EMPTY);
		// 取得购物车
		ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(request);
		Collection<ShoppingCartItem> items = shoppingCart.getItems();
		Iterator<ShoppingCartItem> iterator = items.iterator();
		while (iterator.hasNext()) {
			ShoppingCartItem shoppingCartItem = (ShoppingCartItem) iterator.next();
			int quantity = shoppingCartItem.getQuantity();
			Integer bookId = shoppingCartItem.getBook().getId();
			String title = shoppingCartItem.getBook().getTitle();
			int storeNumber = 0;
			// 数据库获得该商品准确信息
			Book book = bookService.getBook(bookId);
			if (!Objects.isNull(book)) {
				storeNumber = book.getStoreNumber();
			}

			if (quantity > storeNumber) {
				errors.append(title + "库存数量不足<br>");
			}
		}
		return errors;
	}

	private UserService userService = new UserServiceImpl();

	/**
	 * 用户名与账号匹配校验
	 * 
	 * @param username
	 *            用户名
	 * @param accountId
	 *            账号
	 * @return 校验结果信息
	 */
	private StringBuffer validateUser(String username, String accountId) {
		StringBuffer errors = new StringBuffer(StringUtils.EMPTY);
		User user = userService.getUserByName(username);
		if (Objects.isNull(user) || !StringUtils.equals(String.valueOf(user.getAccountId()), accountId)) {
			errors.append("用户名与账号不匹配");

		}
		return errors;
	}

	/**
	 * 用户名与账户必须输入校验
	 * 
	 * @param username
	 *            用户名
	 * @param accountId
	 *            帐号
	 * @return errors 校验结果信息
	 */
	private StringBuffer validateFormField(String username, String accountId) {
		StringBuffer errors = new StringBuffer();
		if (StringUtils.isBlank(username)) {
			errors.append("用户名不能为空<br>");
		}

		if (StringUtils.isBlank(accountId)) {
			errors.append("账号不能为空");
		} else if (!NumberUtils.isDigits(accountId)) {
			errors.append("账号含有非数字项");
		}

		return errors;
	}
}
