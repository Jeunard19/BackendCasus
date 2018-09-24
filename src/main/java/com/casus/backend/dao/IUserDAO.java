package com.casus.backend.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.casus.backend.model.User;

public interface IUserDAO extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);

	List<User> findAll();

}
