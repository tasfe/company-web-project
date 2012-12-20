package com.team.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.team.jdbc.ConnectionManager;

public class BaseDAO {

	private Connection connection;
	private ConnectionManager connManager;
	private boolean isAutoCommit;

	public boolean isAutoCommit() {
		return isAutoCommit;
	}

	public void setAutoCommit(boolean isAutoCommit) {
		this.isAutoCommit = isAutoCommit;
	}

	public BaseDAO() {
		connManager = ConnectionManager.getConnectionManager();
		connection = connManager.getConnection();
		isAutoCommit = false;
	}

	public BaseDAO(boolean autoCommit) {
		connManager = ConnectionManager.getConnectionManager();
		connection = connManager.getConnection();
		isAutoCommit = autoCommit;
	}

	public ResultSet query(String sql) throws SQLException {
		this.connection.setAutoCommit(true);
		PreparedStatement statement = this.connection.prepareStatement(sql);
		return statement.executeQuery();
	}

	public void releaseConnection() {
		connManager.releaseConnection();
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void closeAutoCommit(){
		try {
			this.connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void openAutoCommit(){
		try {
			this.connection.setAutoCommit(true);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void defaultAutoCommit(){
		openAutoCommit();
	}
	
	public void rollback(){
		try {
			this.connection.rollback();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
