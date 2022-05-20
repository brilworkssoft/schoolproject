package com.arcsapt.sms.bean;

public class ProjectionProp {
	private String tableName;
	private String columnName;
	private String projectionType;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getProjectionType() {
		return projectionType;
	}

	public void setProjectionType(String projectionType) {
		this.projectionType = projectionType;
	}

}
