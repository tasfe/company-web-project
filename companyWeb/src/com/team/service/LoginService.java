package com.team.service;

import java.sql.SQLException;

import com.team.bean.User;
import com.team.dao.UserDAO;
import com.team.exception.NoConnectionException;

/**
 * 
 * @author Allen
 *
 */
public class LoginService {
	
	private UserDAO userDao;

	public LoginService(){
		userDao = new UserDAO();
	}
	
	public User login(String username,String password) throws SQLException, NoConnectionException{
		return this.userDao.login(username, password);
	}
}
