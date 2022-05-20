package com.arcsapt.sms.entity;

import java.util.ArrayList;
import java.util.Collection;

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
import javax.persistence.Table;

@Entity
@Table(name = "role_master")
public class RoleMaster {
	@Id
	@Column(name = "role_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer roleId;

	@Column(name = "role_name", nullable = false)
	private String roleName;

	@Column(name = "active")
	private Boolean active;

	@Embedded
	CommonEntity commonEntity;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "menu_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "menu_id") })
	private Collection<MenuMaster> menuMasters = new ArrayList<MenuMaster>();

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private Collection<UserMaster> userMasters = new ArrayList<UserMaster>();

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public CommonEntity getCommonEntity() {
		return commonEntity;
	}

	public void setCommonEntity(CommonEntity commonEntity) {
		this.commonEntity = commonEntity;
	}

	public Collection<MenuMaster> getMenuMasters() {
		return menuMasters;
	}

	public void setMenuMasters(Collection<MenuMaster> menuMasters) {
		this.menuMasters = menuMasters;
	}

	public Collection<UserMaster> getUserMasters() {
		return userMasters;
	}

	public void setUserMasters(Collection<UserMaster> userMasters) {
		this.userMasters = userMasters;
	};

}
