package com.team.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.team.dao.TemplateDAO;

/**
 * Servlet implementation class searchTemplate
 */
@WebServlet(description = "模板搜索", urlPatterns = { "/searchTemplate" })
public class SearchTemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTemplate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String searchCondition = request.getParameter("search_condition");
		TemplateDAO td = new TemplateDAO();
		List<String> results = td.getSearchResult(searchCondition);
		response.setContentType("text/javascript;charset=utf-8");
		response.getWriter().write(JSONArray.fromObject(results).toString());
	}

}
