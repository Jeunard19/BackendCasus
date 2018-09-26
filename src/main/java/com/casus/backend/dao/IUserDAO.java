package com.casus.backend.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.casus.backend.model.User;

@Component
public interface IUserDAO extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);

	List<User> findAll();

}
