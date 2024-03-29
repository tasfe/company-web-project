package com.team.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.team.bean.CompanyType;
import com.team.bean.Template;
import com.team.dao.TemplateDAO;

/**
 * Servlet implementation class TemplateServlet
 */

public class TemplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TemplateServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeId = request.getParameter("type_id");
		TemplateDAO td = new TemplateDAO();
		List<Template> templates = td.getTemplatesByType(typeId);
		response.setContentType("text/javascript;charset=utf-8");
		response.getWriter().write(JSONArray.fromObject(templates).toString());

	}

}
