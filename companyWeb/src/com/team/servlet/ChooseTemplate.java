package com.team.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.util.FileUtil;

@WebServlet(description = "选择模板", urlPatterns = { "/chooseTemplate.html" })
public class ChooseTemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String templateId = request.getParameter("templateId");
		String templateName = "test";//通过ID获得模版名称
		String companyName = "test";//公司名称 
		
		
		String webRootPath = request.getServletContext().getRealPath("/");
		String templatePath = webRootPath + "templates" + "/" + templateName;
		String companyPath = webRootPath + "companies" + "/" + companyName;
		
		/**
		 * 找到模板的目录
		 * 该目录下有html文件，以及css，js，images等的一些文件夹
		 */
		File file = new File(templatePath);
		if(file.isDirectory()){
			String[] files = file.list();
			for(String fileName : files){
				String filePath = templatePath + "/" + fileName;
				File subFile = new File(filePath);
				if(subFile.isDirectory()){//如果是文件夹，ls就直接拷贝到公司门户的文件夹下
					/**
					 * 之后可以通过将模板的js，css的引用路径写成绝对路径，就不需要将静态文件拷贝到公司目录下了。
					 */
					try {
						System.out.println(filePath);
						FileUtil.copyDirectory(filePath, companyPath + "/" + templateName + "/" + fileName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					/**
					 * 如果是文件，就解析文件里的标签，生成输入信息
					 */
					
					
					
					
					
					
					
				}
				
			}
		}
		
	}

}
