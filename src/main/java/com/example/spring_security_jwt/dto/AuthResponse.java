package com.example.spring_security_jwt.dto;

import lombok.Data;

@Data
public class AuthResponse {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public AuthResponse(String token) {
		this.token = token;
	}
	
}
