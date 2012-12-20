package com.team.dao;

import java.sql.SQLException;

import com.team.util.MD5Util;

/**
 * 
 * @author Allen
 *
 */
public class UserDAO extends BaseDAO {

	public String login(String username, String password) throws SQLException {
		String sql = "select company_name from company where contact_email = ? and password = ?";
		String company_name = "";
		try {
			statement = this.getConnection()
					.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, MD5Util.getMD5String(password));
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				company_name = resultSet.getString("company_name");
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			this.releaseConnection();
		}

		return company_name;
	}

}
