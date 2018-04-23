package com.zym.bookstore.dao.impl;

import org.junit.Test;

import com.zym.bookstore.domain.Book;
import com.zym.bookstore.web.CriteriaBook;
import com.zym.bookstore.web.Page;

public class TestBookDaoImpl {

	BookDaoImpl bookDaoImpl = new BookDaoImpl();

	@Test
	public void testGetBook() {
		System.out.println(bookDaoImpl.getBook(1));
	}

	@Test
	public void testgetTotalBookNumber() {
		CriteriaBook criteriaBook = new CriteriaBook(20, 60, 4);
		System.out.println(bookDaoImpl.getTotalBookNumber(criteriaBook));

	}

	@Test
	public void testGetPageList() {
		CriteriaBook criteriaBook = new CriteriaBook(0, 100, 8);

		System.out.println(bookDaoImpl.getPageList(criteriaBook, 4));
	}

	@Test
	public void testgetStoreNumber() {
		System.out.println(bookDaoImpl.getStoreNumber(4));

	}

	@Test
	public void testgetPage() {
		CriteriaBook criteriaBook = new CriteriaBook(0, 100, 0);
		Page<Book> page = bookDaoImpl.getPage(criteriaBook);
		System.out.println("PageNo:" + page.getPageNo());
		System.out.println("list:" + page.getList());
		System.out.println("totalItemNumber:" + page.getTotalPageNumber());
		System.out.println("PrevPage:" + page.getPrevPage());
		System.out.println("NextPage:" + page.getNextPage());

	}
}
