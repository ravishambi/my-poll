package com.ravish.mypoll.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Signup {
	
	@NotEmpty
	@Size(min = 3, max = 20)
	private String name;
	
	@NotEmpty
	@Size(min = 3, max = 20)
	private String username;
	
	@NotEmpty
	@Email
	@Size(max = 20)
	private String email;
	
	@NotEmpty
	@Size(min = 5, max = 20)
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
