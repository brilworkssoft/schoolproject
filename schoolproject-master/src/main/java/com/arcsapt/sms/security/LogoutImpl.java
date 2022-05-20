package com.arcsapt.sms.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.InMemoryTokenStore;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class LogoutImpl implements LogoutSuccessHandler {

	InMemoryTokenStore tokenstore;

	public InMemoryTokenStore getTokenstore() {
		return tokenstore;
	}

	public void setTokenstore(InMemoryTokenStore tokenstore) {
		this.tokenstore = tokenstore;
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse,
			Authentication paramAuthentication) throws IOException,
			ServletException {
		String tokens = paramHttpServletRequest.getParameter("access_token");
		if (tokens != null) {
			OAuth2AccessToken accessToken = tokenstore.readAccessToken(tokens);
			if (accessToken != null) {
				tokenstore.removeAccessToken(tokens);
				paramHttpServletResponse.getOutputStream().write(
						"\n\tYou Have Logged Out successfully.".getBytes());
			} else {
				paramHttpServletResponse.getOutputStream().write(
						"\n\tNot Valid Access token.".getBytes());
			}

		} else {
			paramHttpServletResponse.getOutputStream().write(
					"Please provide access_token".getBytes());
		}
		// removeaccess(paramHttpServletRequest);

	}

	public void removeaccess(HttpServletRequest req) {

		String tokens = req.getHeader("Authorization");
		System.out.println(tokens);
		String value = tokens.substring(tokens.indexOf(" ")).trim();
		DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(value);
		System.out.println(token);
		tokenstore.removeAccessToken(value);
		System.out.println("\n\tAccess Token Removed Successfully!!!!!!!!");
	}

}