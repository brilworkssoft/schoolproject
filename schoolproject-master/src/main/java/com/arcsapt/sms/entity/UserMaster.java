package com.arcsapt.sms.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user_master")
public class UserMaster {

	public interface CheckLogin {

	}

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	@Column(name = "user_name")
	@NotEmpty(groups = { CheckLogin.class }, message = "Please Enter Username")
	private String userName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	@NotEmpty(groups = { CheckLogin.class }, message = "Please Enter Password")
	private String password;

	@Column(name = "salt")
	private String salt;

	@Column(name = "reset_password_code")
	private String resetPasswordCode;

	@Column(name = "reset_password_code_until")
	private Date resetPasswordCodeUntil;

	@Embedded
	CommonEntity commonEntity;

	@ManyToOne
	@JoinColumn(name = "parent", insertable = false, updatable = false)
	private UserMaster parent;

	@Column(name = "active")
	private Boolean active;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Collection<RoleMaster> roleMasters = new ArrayList<RoleMaster>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "parent", updatable = false, nullable = false)
	private Collection<UserMaster> userMasters = new ArrayList<UserMaster>();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getResetPasswordCode() {
		return resetPasswordCode;
	}

	public void setResetPasswordCode(String resetPasswordCode) {
		this.resetPasswordCode = resetPasswordCode;
	}

	public Date getResetPasswordCodeUntil() {
		return resetPasswordCodeUntil;
	}

	public void setResetPasswordCodeUntil(Date resetPasswordCodeUntil) {
		this.resetPasswordCodeUntil = resetPasswordCodeUntil;
	}

	public CommonEntity getCommonEntity() {
		return commonEntity;
	}

	public void setCommonEntity(CommonEntity commonEntity) {
		this.commonEntity = commonEntity;
	}

	public UserMaster getParent() {
		return parent;
	}

	public void setParent(UserMaster parent) {
		this.parent = parent;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Collection<RoleMaster> getRoleMasters() {
		return roleMasters;
	}

	public void setRoleMasters(Collection<RoleMaster> roleMasters) {
		this.roleMasters = roleMasters;
	}

	public Collection<UserMaster> getUserMasters() {
		return userMasters;
	}

	public void setUserMasters(Collection<UserMaster> userMasters) {
		this.userMasters = userMasters;
	}

}
