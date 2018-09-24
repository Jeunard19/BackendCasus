package com.casus.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.casus.backend.model.RefreshToken;

public interface IRefreshTokenDAO extends CrudRepository<RefreshToken, String> {

}