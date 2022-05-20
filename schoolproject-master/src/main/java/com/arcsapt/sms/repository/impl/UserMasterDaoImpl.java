package com.arcsapt.sms.repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.arcsapt.sms.entity.UserMaster;
import com.arcsapt.sms.repository.UserMasterDao;

@Repository(value = "userMasterDao")
public class UserMasterDaoImpl implements UserMasterDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@SuppressWarnings("unchecked")
	@Override
	public Boolean isLoginUserExist(String userName, String password) {
		StringBuilder objSB = null;
		Query objQuery = null;
		Boolean isUserExist = false;
		List<String> objUserPassword = null;
		try {
			objSB = new StringBuilder();
			objSB.delete(0, objSB.length())
					.append("SELECT user.password FROM UserMaster AS user")
					.append(" WHERE user.userName='").append(userName)
					.append("' OR user.email='").append(userName).append("'");
			objQuery = this.sessionFactory.getCurrentSession().createQuery(
					objSB.toString());
			objUserPassword = objQuery.list();
			if (objUserPassword != null && objUserPassword.size() == 1) {
				if (this.bCryptPasswordEncoder.matches(password,
						objUserPassword.get(0))) {
					isUserExist = true;
				}
			}
		} finally {
			if (objUserPassword != null) {
				objUserPassword.clear();
				objUserPassword = null;
			}
			if (objSB != null) {
				objSB.delete(0, objSB.length());
				objSB = null;
			}
			objQuery = null;
		}
		return isUserExist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean isUserExist(String userName) {
		StringBuilder objSB = null;
		Query objQuery = null;
		Boolean isUserExist = false;
		List<String> objUserNames = null;
		try {
			objSB = new StringBuilder();
			objSB.delete(0, objSB.length())
					.append("SELECT user.userName FROM UserMaster AS user")
					.append(" WHERE user.userName='").append(userName)
					.append("' OR user.email='").append(userName).append("'");
			objQuery = this.sessionFactory.getCurrentSession().createQuery(
					objSB.toString());
			objUserNames = objQuery.list();
			if (objUserNames != null && objUserNames.size() == 1) {
				isUserExist = true;
			}
		} finally {
			if (objUserNames != null) {
				objUserNames.clear();
				objUserNames = null;
			}
			if (objSB != null) {
				objSB.delete(0, objSB.length());
				objSB = null;
			}
			objQuery = null;
		}
		return isUserExist;
	}

	@SuppressWarnings("unchecked")
	public UserMaster getUserByUserNameOrEmail(String userName) {
		UserMaster objUserMaster = null;
		List<UserMaster> objListUsers = null;
		Query objQuery = null;
		StringBuilder objSB = null;
		try {
			objSB = new StringBuilder();
			objSB.delete(0, objSB.length())
					.append("SELECT user FROM UserMaster AS user")
					.append(" WHERE (user.userName='").append(userName)
					.append("' OR user.email='").append(userName)
					.append("') AND user.active=").append(true);
			objQuery = this.sessionFactory.getCurrentSession().createQuery(
					objSB.toString());
			objListUsers = objQuery.list();
			if (objListUsers != null && objListUsers.size() == 1) {
				objUserMaster = objListUsers.get(0);
			}

		} finally {

		}
		return objUserMaster;
	}

	@SuppressWarnings("unchecked")
	public List<String> getUserRoles(String userName) {
		List<String> roles = null;
		Query objQuery = null;
		StringBuilder objSB = null;
		try {
			objSB = new StringBuilder();
			objSB.delete(0, objSB.length())
					.append("SELECT role.roleName FROM UserMaster user")
					.append(" JOIN user.roleMasters role ")
					.append(" WHERE user.userName=:userName")
					.append(" OR user.email=:userName");
			objQuery = this.sessionFactory.getCurrentSession().createQuery(
					objSB.toString());
			objQuery.setString("userName", userName);
			roles = objQuery.list();
		} finally {

		}
		return roles;
	}
}
