package com.team.service;

import java.sql.SQLException;

import com.team.bean.Company;
import com.team.dao.CompanyDAO;

public class CompanyService {

	private CompanyDAO companyDAO;

	public CompanyService() {
		companyDAO = new CompanyDAO();
	}

	public boolean addCompany(Company company) throws SQLException {
		return this.companyDAO.addCompany(company);
	}

	public boolean deleteCompany(Company company) throws SQLException {
		return this.companyDAO.deleteCompany(company);
	}

	public boolean updateCompany(Company company) throws SQLException {
		return this.companyDAO.updateCompany(company);
	}
}
