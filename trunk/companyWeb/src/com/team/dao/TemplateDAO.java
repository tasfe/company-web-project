package com.team.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team.bean.CompanyType;
import com.team.bean.Template;
import com.team.util.DBTool;
import com.team.util.DBUtil;

public class TemplateDAO {
	private DBTool db = null;
	private Connection conn = null;
	public TemplateDAO(){
		db = new DBTool();
		conn = DBTool.getConnection("jdbc:mysql://192.168.1.20:3306/companyweb","root","root");
		//conn = DBUtil.getConnection();
	}
	
	//获取模板类型
	public  List<CompanyType> getTemplateTypes(){
		PreparedStatement ps = null;
	 	ResultSet rs = null;
		List typeList = new ArrayList();
		String sql = "select type_id, type_name, default_template_id from company_type";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				CompanyType companyType = new CompanyType();
				companyType.setTypeId(rs.getInt("type_id"));
				companyType.setTypeName(rs.getString("type_name"));
				companyType.setDefaultTemplateId(rs.getInt("default_template_id"));
				typeList.add(companyType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBTool.close(conn, ps, rs);
		}
		return typeList;
	}
	
	//根据类型获取模板
	public List<Template> getTemplatesByType(String typeId){
		PreparedStatement ps = null;
		ResultSet rs = null;
		List templateList = new ArrayList();
		String sql = "select template_id, type_id, template_name, template_xml_path from template where type_id = " + typeId;
		if(typeId == null || typeId.trim() == ""){
			sql = "select template_id, type_id, template_name, template_xml_path from template";
		}
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Template template = new Template();
				template.setTemplateId(rs.getInt("template_id"));
				template.setTypeId(rs.getInt("type_id"));
				template.setTemplateName(rs.getString("template_name"));
				template.setTemplateXmlPath(rs.getString("template_xml_path"));
				templateList.add(template);
			}
		} catch (SQLException e) {
			e.printStackTrace();	
		}finally{
			DBTool.close(conn, ps, rs);
		}
		return templateList;
	}
	
	//搜索
	public List<String> getSearchResult(String condition){
		PreparedStatement ps = null;
		ResultSet rs = null;
		List resultList = new ArrayList();
		String sql = "select template_name from template where template_name like \'%" + condition + "%\' limit 5";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				resultList.add(rs.getString("template_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBTool.close(conn, ps, rs);
		}
		return resultList;
	}
}
