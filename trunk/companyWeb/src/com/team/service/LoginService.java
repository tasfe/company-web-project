package com.team.service;

import com.team.dao.UserDAO;

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
	
	public String login(String username,String password){
		return this.userDao.login(username, password);
	}
}
