package com.team.bean;

import java.util.Date;

/**
 * 
 * @author Allen
 *
 */
public class Company {

	private int company_id;
	private String company_name;
	private String password;
	private String business_license;
	private Date create_date;
	private String contact_phone;
	private String contact_email;
	private String contact_user;
	private String web_path;
	private String company_address;
	private int type_id;

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBusiness_license() {
		return business_license;
	}

	public void setBusiness_license(String business_license) {
		this.business_license = business_license;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public String getContact_email() {
		return contact_email;
	}

	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}

	public String getContact_user() {
		return contact_user;
	}

	public void setContact_user(String contact_user) {
		this.contact_user = contact_user;
	}

	public String getWeb_path() {
		return web_path;
	}

	public void setWeb_path(String web_path) {
		this.web_path = web_path;
	}

	public String getCompany_address() {
		return company_address;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

}
