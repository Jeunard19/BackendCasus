package com.casus.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casus.backend.dao.IUserDAO;
import com.casus.backend.model.User;
import com.casus.backend.model.UserPrincipal;

@Service
@Transactional(readOnly=false)
public class UserService implements UserDetailsService, IUserService {

	@Autowired
	private IUserDAO iUserDAO;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<User> optional = this.iUserDAO.findByUsername(username);
		if (optional.isPresent()) {
			return new UserPrincipal(optional.get());
		}

		throw new UsernameNotFoundException(username);
	}

	@Override
	public User register(User user) {
		user.setPassword(this.hashpaswword(user.getPassword()));
		return this.iUserDAO.save(user);
	}

	@Override
	public User update(Optional<User> user) {
		//user.setPassword(this.hashpaswword(user.getPassword()));
		Optional<User> user2=this.findById(user.get().getId());
		user.get().setEmail(user2.get().getEmail());
		user.get().setName(user2.get().getName());
		user.get().setPassword(user2.get().getPassword());
		user.get().setRole(user2.get().getRole());
		user.get().setUsername(user2.get().getUsername());
		return this.iUserDAO.save(user.get());
	}

	@Override
	public void delete(User user) {
		this.iUserDAO.delete(user);
	}

	@Override
	public List<User> findAll() {
		return this.iUserDAO.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		return this.iUserDAO.findById(id);
	}
	public String hashpaswword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		return "{bcrypt}"+hashedPassword;

	}

	//@Override
	//public int finduserid(String username) {
		// TODO Auto-generated method stub
	//	return iUserDAO.findUserId(username);
//	}
	
}
