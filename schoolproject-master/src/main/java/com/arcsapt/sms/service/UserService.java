package com.arcsapt.sms.service;

import java.util.List;

import com.arcsapt.sms.dto.ResponseDTO;
import com.arcsapt.sms.entity.UserMaster;

public interface UserService {
	public ResponseDTO login(UserMaster userMaster);

	public Boolean isLoginUserExist(String userName, String password,
			Boolean isSNSLogin);

	public UserMaster getUserByUserNameOrEmail(String userName);

	public List<String> getUserRoles(String userName);
}
