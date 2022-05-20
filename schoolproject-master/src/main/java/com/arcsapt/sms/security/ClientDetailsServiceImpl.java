package com.arcsapt.sms.security;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.BaseClientDetails;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Override
	public ClientDetails loadClientByClientId(String clientId)
			throws OAuth2Exception {

		if (clientId.equals("hr")) {

			List<String> authorizedGrantTypes = new ArrayList<String>();
			authorizedGrantTypes.add("password");
			authorizedGrantTypes.add("refresh_token");
			authorizedGrantTypes.add("client_credentials");
			authorizedGrantTypes.add("authorization_code");
			authorizedGrantTypes.add("implicit");

			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

			List<String> scope = new ArrayList<String>();
			scope.add("read");
			scope.add("write");
			scope.add("trust");

			BaseClientDetails clientDetails = new BaseClientDetails();
			clientDetails.setClientId("hr");
			clientDetails.setClientSecret("hr");
			clientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
			clientDetails.setAuthorities(grantedAuthorities);
			clientDetails.setScope(scope);
			return clientDetails;

		} else if (clientId.equals("demo")) {

			List<String> authorizedGrantTypes = new ArrayList<String>();
			// authorizedGrantTypes.add("password");
			authorizedGrantTypes.add("client_credentials");
			authorizedGrantTypes.add("refresh_token");
			// authorizedGrantTypes.add("client_credentials");
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			grantedAuthorities
					.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));

			BaseClientDetails clientDetails = new BaseClientDetails();
			clientDetails.setClientId("demo");
			clientDetails.setClientSecret("demo");
			clientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
			clientDetails.setAuthorities(grantedAuthorities);
			return clientDetails;
		}

		else {
			throw new NoSuchClientException("No client with requested id: "
					+ clientId);
		}
	}

}