package com.world2meettest.app.apikeyauthentication.configuration;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.servlet.http.HttpServletRequest;

public class AuthenticationService {

	private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
	private static final String AUTH_TOKEN = "w2m";

	public static Authentication getAuthentication(HttpServletRequest request) {
		String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);

		String uri = request.getRequestURI().substring(0, 4);

		if (uri.equals("/api")) {
			if (apiKey == null || !apiKey.equals(AUTH_TOKEN)) {
				throw new BadCredentialsException("Invalid API Key");
			}
		}

		return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
	}

}
