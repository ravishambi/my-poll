package com.ravish.mypoll.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ravish.mypoll.model.Role;
import com.ravish.mypoll.model.User;
import com.ravish.mypoll.model.RoleName;
import com.ravish.mypoll.payload.ApiResponse;
import com.ravish.mypoll.payload.Signup;
import com.ravish.mypoll.repository.RoleRepository;
import com.ravish.mypoll.repository.UserRepository;

@RestController 
public class AuthController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/hello")
	public String hello() {
		
		return "Hello";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@GetMapping("/secured/hello")
	public String securedHello() {
		
		return "secured Hello";
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody Signup signup){
		
		if(userRepository.existsByUsername(signup.getUsername())) {
			return new ResponseEntity(new ApiResponse(false, "Username already exists"), 
					HttpStatus.BAD_REQUEST);
		}
		
		if(userRepository.existsByEmail(signup.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Email already exists"), 
					HttpStatus.BAD_REQUEST);
		}
		
		User user = new User(signup.getName(), signup.getUsername(),
				signup.getEmail(), signup.getPassword());
		
		Set<Role> roles = new HashSet<>();
		Optional<Role> role = roleRepository.findByName(RoleName.ROLE_USER);
		if(role.isPresent()) {
			
			roles.add(role.get());
		}
		user.setRoles(roles);
		userRepository.save(user);
		
		
		return ResponseEntity.ok("200");
		
	}

}
