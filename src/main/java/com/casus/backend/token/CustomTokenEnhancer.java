package com.casus.backend.token;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.casus.backend.model.UserPrincipal;

@Component
public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> additionalInfo = new HashMap<>();

		UserPrincipal userPrincipal = (UserPrincipal) authentication.getUserAuthentication().getPrincipal();

		additionalInfo.put("id", String.valueOf(userPrincipal.getId()));
		additionalInfo.put("name", String.valueOf(userPrincipal.getName()));

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}

}
