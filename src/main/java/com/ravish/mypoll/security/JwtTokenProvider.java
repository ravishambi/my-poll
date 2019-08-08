package com.ravish.mypoll.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

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
	
	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
		
	}
	
	public boolean validateToken(String token) {
		
		try {
			Jwts.parser().setSigningKey(secret)
			.parseClaimsJws(token);
		}
		catch(SignatureException ex) {
			System.out.println("Invalid token");
			return false;
		}
		catch(MalformedJwtException ex) {
			System.out.println("Malformed token");
			return false;
		}
		catch(ExpiredJwtException ex) {
			System.out.println("Expired token");
			return false;
		}
		catch(UnsupportedJwtException ex) {
			System.out.println("Unsupported token");
			return false;
		}
		catch(IllegalArgumentException ex) {
			System.out.println("Illegal argument token");
			return false;
		}
			
		return true;
	}

}
