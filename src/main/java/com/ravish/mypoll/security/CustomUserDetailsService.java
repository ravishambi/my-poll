package com.ravish.mypoll.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ravish.mypoll.model.User;
import com.ravish.mypoll.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> optionalUser = userRepository.findByEmailOrUsername(username, username);
		
		optionalUser
		.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		
		return optionalUser
		.map(CustomUserDetails::new)
		.get();
		
	}

}
