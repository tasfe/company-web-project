package com.team.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTool {
	
	public static Connection getConnection(String dbUrl, String name, String passwd) {
        Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, name, passwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
			try {
				if(rs !=null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				try {
					if(ps != null){
						ps.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
					try {
						if(con != null){
							con.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
	}
}
