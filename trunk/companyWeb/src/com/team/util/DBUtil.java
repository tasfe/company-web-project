package com.team.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;


public class DBUtil {
	private static final String URL = MessageFormat.format(
			ConfigUtil.getValue("jdbc.url"), ConfigUtil.getValue("db.host"),
			ConfigUtil.getValue("db.port"), ConfigUtil.getValue("db.name"));
	private static final String USER = ConfigUtil.getValue("db.user");
	private static final String PASSWORD = ConfigUtil.getValue("db.password");
	
	static {
		try {
			Class.forName(ConfigUtil.getValue("jdbc.driver"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
