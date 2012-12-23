package com.team.dao;

import java.sql.SQLException;
import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;

import com.team.bean.User;
import com.team.exception.NoConnectionException;
import com.team.util.MD5Util;

/**
 * 
 * @author Allen
 * 
 */
public class UserDAO extends BaseDAO {

	public User login(String email, String password) throws SQLException,
			NoConnectionException {
		String sql = "select user_id,user_name,user_email,user_register_date from company where user_email = ? and user_password = ?";
		User user = null;
		try {
			statement = this.getConnection().prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, MD5Util.getMD5String(password));
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setUserId(resultSet.getInt("user_id"));
				user.setUserName(resultSet.getString("user_name"));
				user.setEmail(resultSet.getString("user_email"));
				user.setRegistDate(resultSet.getTimestamp("user_register_date"));
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			this.releaseConnection();
		}

		return user;
	}

	public User regist(User user) throws SQLException, NoConnectionException {
		String sql = "insert into usr_usr(user_name,user_password,user_email) values(?,?,?)";
		User result = null;
		boolean isInsert = false;
		try {
			this.closeAutoCommit();
			statement = this.getConnection().prepareStatement(sql);
			statement.setString(1, StringUtils.isEmpty(user.getUserName()) ? ""
					: user.getUserName());

			statement.setString(
					2,
					StringUtils.isEmpty(user.getUserPassword()) ? "" : MD5Util
							.getMD5String(user.getUserPassword()));

			statement.setString(3, StringUtils.isEmpty(user.getEmail()) ? ""
					: user.getEmail());

			isInsert = statement.execute();

			if (isInsert) {
				sql = "select user_id,user_name,user_email,user_register_date from company where user_email = '{0}' and user_password = '{1}'";
				resultSet = statement.executeQuery(MessageFormat.format(sql,
						user.getEmail(),
						MD5Util.getMD5String(user.getUserPassword())));
				while (resultSet.next()) {
					result = new User();
					result.setUserId(resultSet.getInt("user_id"));
					result.setUserName(resultSet.getString("user_name"));
					result.setEmail(resultSet.getString("user_email"));
					result.setRegistDate(resultSet
							.getTimestamp("user_register_date"));
				}
			}

			return result;
		} catch (SQLException e) {
			this.rollback();
			throw e;
		} finally {
			this.releaseConnection();
		}
	}

}
