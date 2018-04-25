package com.zym.bookstore.web;

import java.sql.Connection;

public class ConnectionContext {

	private static ConnectionContext connectionContext = new ConnectionContext();

	public ConnectionContext() {
	}

	public static ConnectionContext getInstance() {

		return connectionContext;
	}

	private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

	public void bind(Connection connection) {
		connectionThreadLocal.set(connection);
	}

	public void Remove() {
		connectionThreadLocal.remove();
	}

	public Connection get() {
		return connectionThreadLocal.get();
	}
}
