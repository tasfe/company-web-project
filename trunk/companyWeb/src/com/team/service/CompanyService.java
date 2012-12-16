package com.team.service;

import com.team.bean.Company;
import com.team.dao.CompanyDAO;

public class CompanyService {

	private CompanyDAO companyDAO;

	public CompanyService() {
		companyDAO = new CompanyDAO();
	}

	public boolean addCompany(Company company) {
		return this.companyDAO.addCompany(company);
	}

	public boolean deleteCompany(Company company) {
		return this.companyDAO.deleteCompany(company);
	}

	public boolean updateCompany(Company company) {
		return this.updateCompany(company);
	}
}
