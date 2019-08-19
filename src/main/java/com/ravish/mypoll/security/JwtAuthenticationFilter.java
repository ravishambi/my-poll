package com.ravish.mypoll.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = getJwtTokenFromRequest(request);
			if(jwtTokenProvider.validateToken(token)) {
				long userId = jwtTokenProvider.getUserIdFromToken(token);
				
				UserDetails userDetails = userDetailsService.loadUserById(userId);
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		catch(Exception ex) {
			System.out.println("unable to store user authentication in security context" + ex);
		}
		
		filterChain.doFilter(request, response);
		
		
	}
	
	private String getJwtTokenFromRequest(HttpServletRequest request) {
		
		Enumeration<String> headers = request.getHeaderNames();
		while(headers.hasMoreElements()) {
			System.out.println(headers.nextElement());
		}
		
		String authorizationToken = request.getHeader("Authorization");
		authorizationToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTY2MTQ1NDA4LCJleHAiOjE1NjYyMDU0MDh9.qIWuwMUxzXDiMlQ1VmwY7dmHKRo5Q79JzDUYatobBByfAbntCj2xidNcgHGuWpxMhT-MWQhF92Ks-cxl5zMbTQ";
		if(!StringUtils.isEmpty(authorizationToken) && StringUtils.startsWithIgnoreCase(authorizationToken, "Bearer ")) {
			authorizationToken = authorizationToken.substring(7);
		}
		
		return authorizationToken;
		
	}
	

}
