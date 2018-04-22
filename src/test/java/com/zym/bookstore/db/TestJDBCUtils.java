package com.zym.bookstore.db;

import static org.junit.Assert.*;

import org.junit.Test;

import com.zym.bookstore.db.JDBCUtils;

public class TestJDBCUtils {

	@Test
	public void testGetConnection() {
		System.out.println(JDBCUtils.getConnection());

	}

	@Test
	public void testRelease() {
		fail("Not yet implemented");
	}

	@Test
	public void testRelea() {
		fail("Not yet implemented");
	}

}
