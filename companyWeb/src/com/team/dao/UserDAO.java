package com.team.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team.util.MD5Util;

/**
 * 
 * @author Allen
 *
 */
public class UserDAO extends BaseDAO {

	public String login(String username, String password) {
		String sql = "select company_name from company where company_name = ? and password = ?";
		String company_name = "";
		try {
			PreparedStatement statement = this.getConnection()
					.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, MD5Util.getMD5String(password));
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				company_name = result.getString("company_name");
			}
			
			result.close();
			statement.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			this.releaseConnection();
		}

		return company_name;
	}
}
