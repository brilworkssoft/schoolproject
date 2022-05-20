package com.arcsapt.sms.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.arcsapt.sms.common.Constants;
import com.arcsapt.sms.service.UserService;

public class CustomUserAuthenticationProvider implements AuthenticationProvider {
	@Resource(name = Constants.SERVICE_USER)
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private Boolean isSNSLogin;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		Boolean isUserExist = this.userService.isLoginUserExist(authentication
				.getPrincipal().toString(), authentication.getCredentials()
				.toString(), isSNSLogin);

		/*
		 * UserMaster objUserMaster = this.userService
		 * .getUserByUserNameOrEmail(authentication.getPrincipal() .toString());
		 */
		if (isUserExist) {
			List<String> userRole = this.userService
					.getUserRoles(authentication.getPrincipal().toString());
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			for (String role : userRole) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role));
			}
			Authentication auth = new UsernamePasswordAuthenticationToken(
					authentication.getPrincipal(),
					authentication.getCredentials(), grantedAuthorities);

			return auth;

		} else {
			throw new BadCredentialsException("Bad User Credentials.");
		}

		/*
		 * if (authentication.getPrincipal().equals("hr") &&
		 * authentication.getCredentials().equals("hr")) {
		 * 
		 * List<GrantedAuthority> grantedAuthorities = new
		 * ArrayList<GrantedAuthority>(); grantedAuthorities.add(new
		 * SimpleGrantedAuthority("ROLE_USER"));
		 * 
		 * Authentication auth = new UsernamePasswordAuthenticationToken(
		 * authentication.getPrincipal(), authentication.getCredentials(),
		 * grantedAuthorities);
		 * 
		 * return auth;
		 * 
		 * } else if (authentication.getPrincipal().equals("demo") &&
		 * authentication.getCredentials().equals("demo")) {
		 * List<GrantedAuthority> grantedAuthorities = new
		 * ArrayList<GrantedAuthority>(); grantedAuthorities.add(new
		 * SimpleGrantedAuthority("ROLE_USER"));
		 * 
		 * Authentication auth = new UsernamePasswordAuthenticationToken(
		 * authentication.getPrincipal(), authentication.getCredentials(),
		 * grantedAuthorities); return auth; } else if
		 * (authentication.getPrincipal().equals("user1") &&
		 * authentication.getCredentials().equals("user1")) {
		 * List<GrantedAuthority> grantedAuthorities = new
		 * ArrayList<GrantedAuthority>(); grantedAuthorities.add(new
		 * SimpleGrantedAuthority("ROLE_USER"));
		 * 
		 * Authentication auth = new UsernamePasswordAuthenticationToken(
		 * authentication.getPrincipal(), authentication.getCredentials(),
		 * grantedAuthorities); return auth; } else { throw new
		 * BadCredentialsException("Bad User Credentials."); }
		 */
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {

		// return true;
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public Boolean getIsSNSLogin() {
		return isSNSLogin;
	}

	public void setIsSNSLogin(Boolean isSNSLogin) {
		this.isSNSLogin = isSNSLogin;
	}

}