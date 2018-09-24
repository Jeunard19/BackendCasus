package com.casus.backend.store;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import com.casus.backend.dao.IAccessTokenDAO;
import com.casus.backend.dao.IRefreshTokenDAO;
import com.casus.backend.model.AccessToken;
import com.casus.backend.model.RefreshToken;

/**
 * Based on the excellent JdbcTokenStore done by Ken Dombeck, Luke Taylor, Dave
 * Syer which can be found at
 * https://github.com/spring-projects/spring-security-oauth
 * 
 * @author Martijn Hiemstra
 */
@Component
public class OauthTokenStore implements TokenStore {

	private static final Logger LOGGER = LoggerFactory.getLogger(OauthTokenStore.class);

	private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

	@Autowired
	private IAccessTokenDAO iAccessTokenDAO;

	@Autowired
	private IRefreshTokenDAO iRefreshTokenDAO;

	@Override
	public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
		return readAuthentication(token.getValue());
	}

	@Override
	public OAuth2Authentication readAuthentication(String token) {
		LOGGER.debug("readAuthentication: token [{}]", token);

		Optional<AccessToken> optional = this.iAccessTokenDAO.findById(token);

		if (optional.isPresent()) {
			LOGGER.debug("Found token {} in the database", token);

			return this.deserializeAuthentication(optional.get().getAuthentication());
		}

		LOGGER.debug("Could not find token {} in the database", token);

		return null;
	}

	@Override
	public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		LOGGER.debug("storeAccessToken");

		String refreshToken = null;

		if (token.getRefreshToken() != null) {
			refreshToken = token.getRefreshToken().getValue();
		}

		if (readAccessToken(token.getValue()) != null) {
			removeAccessToken(token.getValue());
		}

		AccessToken newAccessToken = new AccessToken();
		newAccessToken.setAccessTokenId(token.getValue());
		newAccessToken.setToken(serializeAccessToken(token));
		newAccessToken.setAuthenticationId(authenticationKeyGenerator.extractKey(authentication));
		newAccessToken.setUsername(authentication.isClientOnly() ? null : authentication.getName());
		newAccessToken.setClientId(authentication.getOAuth2Request().getClientId());
		newAccessToken.setAuthentication(serializeAuthentication(authentication));
		newAccessToken.setRefreshToken(refreshToken);

		this.iAccessTokenDAO.save(newAccessToken);

		LOGGER.debug("Saved accesstoken with TokenId[{}] and RefreshToken [{}]", newAccessToken.getAccessTokenId(),
				newAccessToken.getRefreshToken());
	}

	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		LOGGER.debug("readAccessToken (String): tokenValue [{}]", tokenValue);

		Optional<AccessToken> optional = this.iAccessTokenDAO.findById(tokenValue);
		if (optional.isPresent()) {
			LOGGER.debug("  Found accesstoken with token id [{}]", optional.get().getAccessTokenId());

			return deserializeAccessToken(optional.get().getToken());
		}

		LOGGER.debug("  Could not find access token");

		return null;
	}

	@Override
	public void storeRefreshToken(OAuth2RefreshToken oAuth2RefreshToken, OAuth2Authentication authentication) {
		LOGGER.debug("storeRefreshToken");

		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setAuthentication(serializeAuthentication(authentication));
		refreshToken.setToken(serializeRefreshToken(oAuth2RefreshToken));
		refreshToken.setTokenId(oAuth2RefreshToken.getValue());

		this.iRefreshTokenDAO.save(refreshToken);

		LOGGER.debug("Saved refreshtoken with TokenId[{}]", refreshToken.getTokenId());
	}

	@Override
	public OAuth2RefreshToken readRefreshToken(String tokenValue) {
		LOGGER.debug("readRefreshToken: tokenValue [{}]", tokenValue);

		Optional<RefreshToken> optional = this.iRefreshTokenDAO.findById(tokenValue);
		if (optional.isPresent()) {
			return deserializeRefreshToken(optional.get().getAuthentication());
		}

		return null;
	}

	@Override
	public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
		LOGGER.debug("readAuthenticationForRefreshToken");

		return readAuthentication(token.getValue());
	}

	@Override
	public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
		LOGGER.debug("getAccessToken");

		String key = authenticationKeyGenerator.extractKey(authentication);

		LOGGER.debug("  key [{}]", key);

		Optional<AccessToken> optional = this.iAccessTokenDAO.findByAuthenticationId(key);
		if (optional.isPresent()) {
			OAuth2AccessToken accessToken = deserializeAccessToken(optional.get().getToken());

			removeAccessToken(accessToken.getValue());

			// Keep the store consistent (maybe the same user is represented by this
			// authentication but the details have changed)
			storeAccessToken(accessToken, authentication);

			return accessToken;
		}

		return null;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
		LOGGER.debug("findTokensByClientIdAndUserName");

		return this.iAccessTokenDAO.findByClientIdAndUsername(clientId, userName).stream().map( t -> deserializeAccessToken(t.getToken()) ).collect(Collectors.toList());
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
		LOGGER.debug("findTokensByClientId: clientId [{}]", clientId);

		return this.iAccessTokenDAO.findByClientId(clientId).stream().map( t -> deserializeAccessToken(t.getToken()) ).collect(Collectors.toList());
	}

	/*
	 * 
	 * Remove token methodes
	 * 
	 */

	public void removeAccessToken(OAuth2AccessToken token) {
		LOGGER.debug("removeAccessToken");

		removeAccessToken(token.getValue());
	}

	public void removeAccessToken(String tokenValue) {
		LOGGER.debug("removeAccessToken: tokenValue [{}]", tokenValue);

		Optional<AccessToken> optional = this.iAccessTokenDAO.findById(tokenValue);
		if (optional.isPresent())
			this.iAccessTokenDAO.delete(optional.get());
	}

	public void removeRefreshToken(OAuth2RefreshToken token) {
		LOGGER.debug("removeRefreshToken");

		removeRefreshToken(token.getValue());
	}

	public void removeRefreshToken(String token) {
		LOGGER.debug("removeRefreshToken: token [{}]", token);

		Optional<RefreshToken> optional = this.iRefreshTokenDAO.findById(token);
		if (optional.isPresent())
			this.iRefreshTokenDAO.delete(optional.get());
	}

	@Override
	public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
		LOGGER.debug("removeAccessTokenUsingRefreshToken");

	}

	/*
	 * 
	 * Serialization methodes
	 * 
	 */

	protected byte[] serializeAccessToken(OAuth2AccessToken token) {
		return SerializationUtils.serialize(token);
	}

	protected byte[] serializeRefreshToken(OAuth2RefreshToken token) {
		return SerializationUtils.serialize(token);
	}

	protected byte[] serializeAuthentication(OAuth2Authentication authentication) {
		return SerializationUtils.serialize(authentication);
	}

	protected OAuth2AccessToken deserializeAccessToken(byte[] token) {
		return SerializationUtils.deserialize(token);
	}

	protected OAuth2RefreshToken deserializeRefreshToken(byte[] token) {
		return SerializationUtils.deserialize(token);
	}

	protected OAuth2Authentication deserializeAuthentication(byte[] authentication) {
		return SerializationUtils.deserialize(authentication);
	}

}
