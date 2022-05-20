package com.arcsapt.sms.bean;

import org.hibernate.sql.JoinType;

public class AliasProp {
	private String aliasName;
	private String targetTable;
	private String targetColumn;
	private JoinType joinType;

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getTargetTable() {
		return targetTable;
	}

	public void setTargetTable(String targetTable) {
		this.targetTable = targetTable;
	}

	public String getTargetColumn() {
		return targetColumn;
	}

	public void setTargetColumn(String targetColumn) {
		this.targetColumn = targetColumn;
	}

	public JoinType getJoinType() {
		return joinType;
	}

	public void setJoinType(JoinType joinType) {
		this.joinType = joinType;
	}

}
