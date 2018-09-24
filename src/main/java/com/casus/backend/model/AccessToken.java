package com.casus.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccessToken {

	@Id
	private String accessTokenId;

	private String authenticationId;

	@Column(length = 2000)
	private byte[] token;

	@Column(length = 5000)
	private byte[] authentication;

	private String username;

	private String clientId;

	private String refreshToken;

	public String getAccessTokenId() {
		return accessTokenId;
	}

	public void setAccessTokenId(String accessTokenId) {
		this.accessTokenId = accessTokenId;
	}

	public String getAuthenticationId() {
		return authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	public byte[] getToken() {
		return token;
	}

	public void setToken(byte[] token) {
		this.token = token;
	}

	public byte[] getAuthentication() {
		return authentication;
	}

	public void setAuthentication(byte[] authentication) {
		this.authentication = authentication;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
