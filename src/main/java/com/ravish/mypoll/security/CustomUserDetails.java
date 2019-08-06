package com.ravish.mypoll.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ravish.mypoll.model.User;

@SuppressWarnings("serial")
public class CustomUserDetails extends User implements UserDetails{
	
	public CustomUserDetails(final User user) {
		super(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 return getRoles()
		.stream()
		.map(role -> new SimpleGrantedAuthority(role.getName().name()))
		.collect(Collectors.toList());
		
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
