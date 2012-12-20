package com.team.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.team.jdbc.ConnectionManager;

public class BaseDAO {

	private Connection connection;
	private ConnectionManager connManager;
	protected PreparedStatement statement;
	protected ResultSet resultSet;

	// private boolean isAutoCommit;
	//
	// public boolean isAutoCommit() {
	// return isAutoCommit;
	// }
	//
	// public void setAutoCommit(boolean isAutoCommit) {
	// this.isAutoCommit = isAutoCommit;
	// }

	public BaseDAO() {
		connManager = ConnectionManager.getConnectionManager();
		connection = connManager.getConnection();
		// isAutoCommit = false;
	}

	// public BaseDAO(boolean autoCommit) {
	// connManager = ConnectionManager.getConnectionManager();
	// connection = connManager.getConnection();
	// isAutoCommit = autoCommit;
	// }

	// public ResultSet query(String sql) throws SQLException {
	// this.connection.setAutoCommit(true);
	// PreparedStatement statement = this.connection.prepareStatement(sql);
	// return statement.executeQuery();
	// }

	public void releaseConnection() {
		try {
			this.closeResultSet();
		} catch (SQLException e) {
			//TODO ignore the exception because the next step must execute
		}
		try {
			this.closeStatement();
		} catch (SQLException e) {
			//TODO ignore the exception because the next step must execute
		}
		try {
			this.defaultAutoCommit();
		} catch (SQLException e) {
			//TODO ignore the because the next step must execute
		}
		connManager.releaseConnection();
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void closeAutoCommit() throws SQLException {
		this.connection.setAutoCommit(false);
	}

	public void openAutoCommit() throws SQLException {
		this.connection.setAutoCommit(true);
	}

	public void defaultAutoCommit() throws SQLException {
		openAutoCommit();
	}

	public void rollback() throws SQLException {
		this.connection.rollback();
	}

	public void closeStatement() throws SQLException {
		if (this.statement != null)
			this.statement.close();
	}

	public void closeResultSet() throws SQLException {
		if (this.resultSet != null)
			this.resultSet.close();
	}
}
