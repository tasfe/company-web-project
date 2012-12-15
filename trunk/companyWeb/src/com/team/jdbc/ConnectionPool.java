package com.team.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.team.util.DBUtil;

public class ConnectionPool {

	private BlockingQueue<Connection> pools;
	private int maxNum;
	private int minNum;

	public ConnectionPool() {
		maxNum = 10;
		minNum = maxNum / 2;
		pools = new LinkedBlockingQueue<Connection>(maxNum);
	}

	public ConnectionPool(int maxNum, int minNum) {
		this.maxNum = maxNum;
		this.minNum = minNum;
		pools = new ArrayBlockingQueue<Connection>(this.maxNum);
	}

	public void init() throws InterruptedException {
		for (int i = 0; i < this.minNum; i++) {
			pools.put(DBUtil.getConnection());
		}
	}

	/**
	 * 获取连接
	 * @param timeout
	 * @param unit
	 * @return 如果等待超时，返回null
	 * @throws InterruptedException
	 */
	public Connection getConnection(long timeout,TimeUnit unit) throws InterruptedException {
		return pools.poll(timeout, unit);
	}

	/**
	 * 增加连接池中的连接数，连接池的是数目不超过最大连接数
	 * @throws InterruptedException
	 */
	public void addConnection() throws InterruptedException {
		if (pools.size() < this.maxNum) {
			int factor = (int) Math.ceil((this.maxNum - pools.size()) / 2.0);
			while (factor-- > 0) {
				pools.put(DBUtil.getConnection());
			}
		}
	}

	/**
	 * 释放连接，如果连接池已满，则关闭连接
	 * @param connection
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	public void releaseConnection(Connection connection)
			throws InterruptedException, SQLException {
		if (pools.size() < this.maxNum) {
			pools.put(connection);
		} else {
			connection.close();
		}
	}

	/**
	 * 减少连接池中的连接数
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	public void reduceCoonection() throws InterruptedException, SQLException {
		while (pools.size() > this.minNum) {
			Connection c = pools.take();
			c.close();
		}
	}
}
