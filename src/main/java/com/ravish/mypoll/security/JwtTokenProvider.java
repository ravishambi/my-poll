package com.ravish.mypoll.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
	@Value("${secret}")
	private String secret;
	
	@Value("${expiringInMs}")
	private long expiringInMs;
	
	public String generateToken(Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		Date now = new Date();
		
		return Jwts.builder()
			.setSubject(String.valueOf(userDetails.getId()))
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime()+ expiringInMs))
			.signWith(SignatureAlgorithm.HS512, secret)
			.compact();
		
		
	}
	
	public boolean validateToken(String token) {
		
		
		return false;
	}

}
