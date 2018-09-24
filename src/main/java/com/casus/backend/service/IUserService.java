package com.casus.backend.service;

import java.util.List;
import java.util.Optional;

import com.casus.backend.model.User;

public interface IUserService {
	
	public User register(User user);
	
	public User update(User user);
	
	public void delete(User user);
	
	public List<User> findAll();
	
	public Optional<User> findById( Long id );

}
