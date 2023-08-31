package com.bigboxer23.solar_moon.cognito;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

/** */
public class WithMockAwsCognitoUserSecurityContextFactory implements WithSecurityContextFactory<WithAwsCognitoUser> {
	@Override
	public SecurityContext createSecurityContext(final WithAwsCognitoUser withAwsCognitoUser) {
		Map<String, Object> attributes = new HashMap<>();
		attributes.put("sub", withAwsCognitoUser.username());
		attributes.put("cognito:username", withAwsCognitoUser.username());
		attributes.put("email", withAwsCognitoUser.email());
		attributes.put("cognito:groups", withAwsCognitoUser.roles());

		List<GrantedAuthority> authorities =
				Collections.singletonList(new OAuth2UserAuthority("ROLE_USER", attributes));
		OAuth2User user = new DefaultOAuth2User(authorities, attributes, "sub");

		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(new OAuth2AuthenticationToken(user, authorities, "client-registration-id"));
		return context;
	}
}
