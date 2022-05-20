package com.arcsapt.sms.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.arcsapt.sms.common.Constants;
import com.arcsapt.sms.dto.AccessTokenDTO;
import com.arcsapt.sms.dto.ResponseDTO;
import com.arcsapt.sms.entity.UserMaster;
import com.arcsapt.sms.repository.UserMasterDao;
import com.arcsapt.sms.security.CustomUserAuthenticationProvider;
import com.arcsapt.sms.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "userMasterDao")
	private UserMasterDao userMasterDao;

	@Transactional
	public ResponseDTO login(UserMaster userMaster) {
		ResponseDTO response = null;
		AccessTokenDTO objAccessTokenDTO = null;
		try {
			if (userMaster != null) {
				if (this.userMasterDao.isLoginUserExist(
						userMaster.getUserName(), userMaster.getPassword())) {
					objAccessTokenDTO = this.getPasswordAccessToken(
							Constants.ACCESS_TOKEN_URL,
							Constants.ACCESS_TOKEN_CLIENT_ID,
							Constants.ACCESS_TOKEN_CLIENT_SECRET,
							userMaster.getUserName(), userMaster.getPassword(),
							false);
					if (objAccessTokenDTO != null) {
						response = new ResponseDTO(true, 200,
								objAccessTokenDTO, Constants.STATUS_SUCCESS,
								Constants.LINK_SUCCESS);
					} else {
						response = new ResponseDTO(false, 400,
								"Error In access token generation",
								Constants.STATUS_FAIL,
								Constants.LINK_ERROR_ACCESS_TOEKN_GENERATION);
					}
				} else {
					response = new ResponseDTO(false, 400,
							"Username or password not match",
							Constants.STATUS_FAIL,
							Constants.LINK_ERROR_USER_NOT_FOUND);
				}
			}
		} finally {

		}
		return response;
	}

	@Transactional
	public Boolean isLoginUserExist(String userName, String password,
			Boolean isSNSLogin) {
		Boolean isUserExist = false;

		try {
			if (isSNSLogin != null && isSNSLogin) {
				isUserExist = this.userMasterDao.isUserExist(userName);
			} else {
				System.out.println("sns off");
				isUserExist = this.userMasterDao.isLoginUserExist(userName,
						password);
			}
		} finally {

		}
		return isUserExist;
	}

	@Transactional
	public UserMaster getUserByUserNameOrEmail(String userName) {
		UserMaster objUserMaster = null;
		try {
			objUserMaster = this.userMasterDao
					.getUserByUserNameOrEmail(userName);
		} finally {

		}
		return objUserMaster;
	}

	public AccessTokenDTO getPasswordAccessToken(String url, String clientId,
			String clientSecret, String userName, String password,
			Boolean isSNSLogin) {
		AccessTokenDTO objAccessToken = null;
		RestTemplate objRestTemplate = null;
		StringBuilder objSB = null;
		try {
			objRestTemplate = new RestTemplate();
			objSB = new StringBuilder();

			objSB.delete(0, objSB.length()).append(url)
					.append(Constants.STRING_QUESTION)
					.append("grant_type=password").append(Constants.STRING_AND)
					.append("client_id").append(Constants.STRING_EQUAL)
					.append(clientId).append(Constants.STRING_AND)
					.append("client_secret").append(Constants.STRING_EQUAL)
					.append(clientSecret).append(Constants.STRING_AND)
					.append("username").append(Constants.STRING_EQUAL)
					.append(userName).append(Constants.STRING_AND)
					.append("password").append(Constants.STRING_EQUAL)
					.append(password);

			customUserAuthenticationProvider.setIsSNSLogin(isSNSLogin);
			objAccessToken = objRestTemplate.getForObject(objSB.toString(),
					AccessTokenDTO.class);
		} catch (HttpClientErrorException httpClientErrorException) {
			httpClientErrorException.printStackTrace();
			objAccessToken = null;
		} finally {
			if (objSB != null) {
				objSB.delete(0, objSB.length());
				objSB = null;
			}
		}
		return objAccessToken;
	}

	@Autowired
	private CustomUserAuthenticationProvider customUserAuthenticationProvider;

	@Transactional
	public List<String> getUserRoles(String userName) {
		return this.userMasterDao.getUserRoles(userName);
	}
}
