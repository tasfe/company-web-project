package com.team.bean;

public class CompanyType {
	private int typeId;
	private String typeName;
	private int defaultTemplateId;
	
	public int getTypeId() {
		return typeId;
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public int getDefaultTemplateId() {
		return defaultTemplateId;
	}
	
	public void setDefaultTemplateId(int defaultTemplateId) {
		this.defaultTemplateId = defaultTemplateId;
	}
}
