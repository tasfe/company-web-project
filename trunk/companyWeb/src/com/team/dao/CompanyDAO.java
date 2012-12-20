package com.team.dao;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import com.team.bean.Company;
import com.team.util.MD5Util;

/**
 * 
 * @author Allen
 * 
 */
public class CompanyDAO extends BaseDAO {

	public boolean addCompany(Company company) {
		String sql = "insert into company (company_name,password,"
				+ "business_license,contact_phone,contact_email,contact_user,"
				+ "web_path,company_address,type_id) values (?,?,?,?,?,?,?,?,?)";
		boolean result = false;
		try {
			this.closeAutoCommit();
			statement = this.getConnection()
					.prepareStatement(sql);
			statement.setString(1,
					StringUtils.isEmpty(company.getCompanyName()) ? ""
							: company.getCompanyName());

			statement.setString(
					2,
					StringUtils.isEmpty(company.getPassword()) ? "" : MD5Util
							.getMD5String(company.getCompanyName()));

			statement.setString(3, StringUtils.isEmpty(company
					.getBusinessLicense()) ? "" : company.getBusinessLicense());

			statement.setString(4, StringUtils.isEmpty(company
					.getContactPhone()) ? "" : company.getContactPhone());

			statement.setString(5, StringUtils.isEmpty(company
					.getContactEmail()) ? "" : company.getContactEmail());

			statement.setString(6,
					StringUtils.isEmpty(company.getContactUser()) ? ""
							: company.getContactUser());

			statement.setString(
					7,
					StringUtils.isEmpty(company.getWebPath()) ? "" : company
							.getWebPath());

			statement.setString(8, StringUtils.isEmpty(company
					.getCompanyAddress()) ? "" : company.getCompanyAddress());

			statement.setInt(9, company.getTypeId());
			result = statement.execute();
			return result;
		} catch (SQLException e) {
			this.rollback();
			throw new RuntimeException(e);
		} finally {
			this.releaseConnection();
		}
	}

	public boolean updateCompany(Company company) {
		String sql = "update company set company_name = ?,password = ?,"
				+ "business_license = ?,contact_phone = ?,contact_email = ?,"
				+ "contact_user = ?,web_path = ?,company_address = ?,"
				+ "type_id = ? set company_id = ?";

		try {
			this.closeAutoCommit();
			statement = this.getConnection()
					.prepareStatement(sql);
			statement.setString(1,
					StringUtils.isEmpty(company.getCompanyName()) ? ""
							: company.getCompanyName());

			statement.setString(
					2,
					StringUtils.isEmpty(company.getPassword()) ? "" : MD5Util
							.getMD5String(company.getCompanyName()));

			statement.setString(3, StringUtils.isEmpty(company
					.getBusinessLicense()) ? "" : company.getBusinessLicense());

			statement.setString(4, StringUtils.isEmpty(company
					.getContactPhone()) ? "" : company.getContactPhone());

			statement.setString(5, StringUtils.isEmpty(company
					.getContactEmail()) ? "" : company.getContactEmail());

			statement.setString(6,
					StringUtils.isEmpty(company.getContactUser()) ? ""
							: company.getContactUser());

			statement.setString(
					7,
					StringUtils.isEmpty(company.getWebPath()) ? "" : company
							.getWebPath());

			statement.setString(8, StringUtils.isEmpty(company
					.getCompanyAddress()) ? "" : company.getCompanyAddress());

			statement.setInt(9, company.getTypeId());
			statement.setInt(10, company.getCompanyId());

			return statement.execute();
		} catch (SQLException e) {
			this.rollback();
			throw new RuntimeException(e);
		} finally {
			this.releaseConnection();
		}
	}

	public boolean deleteCompany(Company company) {
		String sql = "delete from company where company_id = ?";
		try {
			this.closeAutoCommit();
			statement = this.getConnection()
					.prepareStatement(sql);

			statement.setInt(1, company.getCompanyId());

			return statement.execute();
		} catch (SQLException e) {
			this.rollback();
			throw new RuntimeException(e);
		} finally {
			this.releaseConnection();
		}
	}
}
