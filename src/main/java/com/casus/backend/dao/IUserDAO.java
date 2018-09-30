package com.casus.backend.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.casus.backend.model.User;

@Component
public interface IUserDAO extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);

	List<User> findAll();
	
	//@Query("SELECT p.id FROM user p WHERE p.username:username")
	//int findUserId(@Param("username") String username);
	

}
