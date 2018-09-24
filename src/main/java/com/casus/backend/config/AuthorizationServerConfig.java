package com.casus.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.DefaultUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;

import com.casus.backend.store.OauthTokenStore;
import com.casus.backend.token.CustomTokenEnhancer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static String REALM = "KES_OAUTH_REALM";

	@Autowired
	private OauthTokenStore tokenStore;

	@Autowired
	private UserApprovalHandler userApprovalHandler;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomTokenEnhancer tokenEnhancer;

	@Value("${security.oauth2.client-id}")
	private String clientId;

	@Value("${security.oauth2.client-secret}")
	private String clientSecret;

	@Value("${security.oauth2.authorized-grant-types}")
	private String authorizedGrantTypes;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		String[] authorizedTypes = authorizedGrantTypes.split("\\s*,\\s*");

		clients.inMemory().withClient(clientId).authorizedGrantTypes(authorizedTypes)
				.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT").scopes("read", "write", "trust")
				.secret(this.passwordEncoder.encode(clientSecret)).accessTokenValiditySeconds(300) // Access token is
																									// only valid for 5
																									// minutes.
				.refreshTokenValiditySeconds(1800); // Refresh token is only valid for 30 minutes.
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.tokenStore(tokenStore)
			.userApprovalHandler(userApprovalHandler)
			.tokenEnhancer(tokenEnhancer)
			.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").realm(REALM + "/client");
	}

	@Bean
	public UserApprovalHandler approvalHandler() {
		UserApprovalHandler approvalHandler = new DefaultUserApprovalHandler();
		return approvalHandler;
	}

}