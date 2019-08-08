package com.ravish.mypoll.payload;

public class JwtAuthenticationResponse {
	
	private String tokenType = "Bearer";
	
	private String token;
	
	public JwtAuthenticationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getTokenType() {
		return tokenType;
	}
	
	

}
