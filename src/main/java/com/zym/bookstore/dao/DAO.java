package com.zym.bookstore.dao;

import java.util.List;

/*
 * 为所有的表对象增加CRUD的共同方法
 */
public interface DAO<T> {
	/**
	 * 执行多条记录的查询
	 * 
	 * @param sql
	 *            执行的sql语句
	 * @param args
	 *            查询条件
	 * @return 返回一个多条记录的List
	 */
	List<T> queryForList(String sql, Object... args);

	/**
	 * 执行单条记录的查询
	 * 
	 * @param sql
	 *            执行的sql语句
	 * @param args
	 *            查询条件
	 * @return 返回一个单条查询记录
	 */
	T query(String sql, Object... args);

	/**
	 * 执行更新表
	 * 
	 * @param sql
	 *            更新语句
	 * @param args
	 *            更新条件
	 * @return 返回更新记录得数量
	 */
	void update(String sql, Object... args);

	/**
	 * 执行删除记录的操作
	 * 
	 * @param sql
	 *            删除语句
	 * @param args
	 *            删除条件
	 * @return 返回删除记录数量
	 */
	long insert(String sql, Object... args);

	/**
	 * 查询属性值例如：某条记录的某个字段
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	<E> E getSingleVal(String sql, Object... args);

	/**
	 * 执行批量更新操作
	 * 
	 * @param sql:
	 *            待执行的 SQL 语句
	 * @param args:
	 *            填充占位符的可变参数
	 */
	void batch(String sql, Object[]... args);

}
