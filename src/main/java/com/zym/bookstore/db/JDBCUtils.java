package com.zym.bookstore.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zym.bookstore.exception.DBException;

public class JDBCUtils {
	private static DataSource dataSource;
	static {
		dataSource = new ComboPooledDataSource("bookstore");
	}

	/*
	 * 获取数据库连接
	 */
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("数据库连接错误！");
		}
	}

	/*
	 * 释放数据库连接
	 */
	public static void release(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("数据库连接错误！");
		}
	}

	public static void relea(ResultSet resultSet, Statement statement) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("数据库连接错误!");
		}

		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("数据库连接错误!");
		}
	}
}
