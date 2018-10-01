package com.casus.backend.controller;

import java.security.Principal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.casus.backend.dto.UserDto;
import com.casus.backend.model.User;
import com.casus.backend.service.IUserService;

@Controller
@RestController
public class UserController {
	@Autowired
	IUserService iUserService;

	@GetMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
	
	@PostMapping("/signup")
	public User register(@RequestBody User model) {
		return this.iUserService.register(model);
		
	}
	
	@PostMapping("/update")
	public User update(@RequestBody Optional<User>  model) {
		return this.iUserService.update(model);
		
	}
	

}
