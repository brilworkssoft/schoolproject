package com.arcsapt.sms.repository;

import java.util.List;

import com.arcsapt.sms.entity.UserMaster;

public interface UserMasterDao {
	public Boolean isLoginUserExist(String userName, String password);

	public Boolean isUserExist(String userName);

	public UserMaster getUserByUserNameOrEmail(String userName);
	
	public List<String> getUserRoles(String userName);
}
