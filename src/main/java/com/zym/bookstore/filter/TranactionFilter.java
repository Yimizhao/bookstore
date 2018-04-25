package com.zym.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zym.bookstore.db.JDBCUtils;
import com.zym.bookstore.web.ConnectionContext;

/**
 * Servlet Filter implementation class TranactionFilter
 */
@WebFilter("/*")
public class TranactionFilter extends HttpFilter {

	public TranactionFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 获取链接
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			// 开启事务
			connection.setAutoCommit(false);
			// 绑定链接
			ConnectionContext.getInstance().bind(connection);
			// 把请求转给servlet
			chain.doFilter(request, response);
			// 提交事务
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			// 回滚事务
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/error/error-1.jsp");
		} finally {
			// 删除链接
			ConnectionContext.getInstance().Remove();

			// 释放链接
			JDBCUtils.release(connection);
		}

	}

}
