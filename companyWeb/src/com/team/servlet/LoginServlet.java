package com.team.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.team.service.LoginService;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 5733796076369651732L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("username");
		String password = request.getParameter("password");

		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();

		if (StringUtils.isBlank(uname) || StringUtils.isBlank(password)) {
			writer.write("{'isLogin':fase;'errmsg':'登录信息为空'}");
		} else {
			LoginService service = new LoginService();
			String companyname = service.login(uname, password);
			if (StringUtils.isEmpty(companyname)) {
				writer.write("{'isLogin':fase;'errmsg':'没有该用户,请注册'}");
			} else {
				writer.write("{'isLogin':true;'name':'" + companyname + "'}");
				writer.write(companyname);
			}
		}
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
