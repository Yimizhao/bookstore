package com.zym.bookstore.service;

import com.zym.bookstore.domain.Book;
import com.zym.bookstore.web.CriteriaBook;
import com.zym.bookstore.web.Page;

public interface BookService {
	Page<Book> getPage(CriteriaBook criteriaBook);

}
