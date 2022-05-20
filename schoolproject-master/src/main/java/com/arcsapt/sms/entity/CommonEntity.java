package com.arcsapt.sms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class CommonEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "createdby", insertable = false, updatable = false)
	private UserMaster createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	@ManyToOne
	@JoinColumn(name = "modifyby", insertable = false, updatable = false)
	private UserMaster modifyby;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public UserMaster getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserMaster createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public UserMaster getModifyby() {
		return modifyby;
	}

	public void setModifyby(UserMaster modifyby) {
		this.modifyby = modifyby;
	}

}
