package com.zym.bookstore.service.impl;

import com.zym.bookstore.dao.BookDAO;
import com.zym.bookstore.dao.impl.BookDaoImpl;
import com.zym.bookstore.domain.Book;
import com.zym.bookstore.service.BookService;
import com.zym.bookstore.web.CriteriaBook;
import com.zym.bookstore.web.Page;

public class BookServiceImpl implements BookService {
	private BookDAO bookDAO = new BookDaoImpl();

	@Override
	public Page<Book> getPage(CriteriaBook criteriaBook) {
		return bookDAO.getPage(criteriaBook);
	}

}
