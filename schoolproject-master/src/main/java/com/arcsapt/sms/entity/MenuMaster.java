package com.arcsapt.sms.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "menu_master")
public class MenuMaster{

	@Id
	@Column(name = "menu_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer menuId;

	@Column(name = "menu_name")
	private String menuName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent")
	private MenuMaster parent;

	@Column(name = "menu_order")
	private Integer menuOrder;

	@Column(name = "menu_url")
	private String menuURL;

	@Column(name = "menu_icon")
	@Lob
	private byte[] menuIcon;

	@Column(name = "active")
	private Boolean active;

	@Embedded
	private CommonEntity commonEntity;

	@ManyToMany
	@JoinTable(name = "menu_role", joinColumns = { @JoinColumn(name = "menu_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Collection<RoleMaster> roleMasters = new ArrayList<RoleMaster>();

	@OneToMany(mappedBy = "parent")
	@Fetch(FetchMode.JOIN)
	private Collection<MenuMaster> childs = new ArrayList<MenuMaster>();

	@Transient
	private MultipartFile file;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public MenuMaster getParent() {
		return parent;
	}

	public void setParent(MenuMaster parent) {
		this.parent = parent;
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getMenuURL() {
		return menuURL;
	}

	public void setMenuURL(String menuURL) {
		this.menuURL = menuURL;
	}

	public byte[] getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(byte[] menuIcon) {
		this.menuIcon = menuIcon;
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

	public Collection<RoleMaster> getRoleMasters() {
		return roleMasters;
	}

	public void setRoleMasters(Collection<RoleMaster> roleMasters) {
		this.roleMasters = roleMasters;
	}

	public Collection<MenuMaster> getChilds() {
		return childs;
	}

	public void setChilds(Collection<MenuMaster> childs) {
		this.childs = childs;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
