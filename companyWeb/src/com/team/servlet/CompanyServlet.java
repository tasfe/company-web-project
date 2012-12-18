package com.team.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.team.bean.Company;
import com.team.service.CompanyService;

/**
 * 
 * @author Allen
 * 
 */
public class CompanyServlet extends HttpServlet {

	private static final long serialVersionUID = -3672999285384813614L;

	private static Map<String, Method> methods = Collections
			.synchronizedMap(new HashMap<String, Method>());

	private static Class[] clazz = { HttpServletRequest.class,
			HttpServletResponse.class };

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");

		try {

			Method m = methods.get(methodName);
			if (m == null) {
				m = CompanyServlet.class.getMethod(methodName, clazz);
				methods.put(methodName, m);
			}
			m.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void regist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String companyName = request.getParameter("companyName");
		String companytype = request.getParameter("companytype");

		String contactor = request.getParameter("contactor");
		String contactphone = request.getParameter("contactphone");
		String email = request.getParameter("email");
		String site = request.getParameter("site");
		String address = request.getParameter("address");

		Company company = new Company();
		company.setCompanyAddress(address);
		company.setCompanyName(companyName);
		company.setContactEmail(email);
		company.setWebPath(site);
		company.setContactPhone(contactphone);
		company.setTypeId(Integer.valueOf(companytype));
		company.setContactUser(contactor);

		PrintWriter writer = response.getWriter();
		
		if (StringUtils.isBlank(companyName)) {
			writer.close();
			return;
		}

		if (StringUtils.isBlank(companytype)) {
			writer.close();
			return;
		}

		if (StringUtils.isBlank(email)) {
			writer.close();
			return;
		}

		if (StringUtils.isBlank(address)) {
			writer.close();
			return;
		}

		CompanyService service = new CompanyService();
		boolean isSuccess = service.addCompany(company);

		if (isSuccess) {
			response.sendRedirect("");
		} else {
			response.setContentType("text/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			writer.write("{'flag':false,'errmsg':'对不起，注册不成功，请重试。'}");
			writer.close();
		}

	}

}
