package com.casus.backend.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.casus.backend.model.AccessToken;

public interface IAccessTokenDAO extends CrudRepository<AccessToken, String> {

	Optional<AccessToken> findByAuthenticationId(String authenticationId);

	List<AccessToken> findByClientId(String clientId);

	List<AccessToken> findByClientIdAndUsername(String clientId, String username);

}