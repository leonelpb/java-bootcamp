package com.integrador.E_commerce.DTO;

public class SignupRequest {
	private String username;
	private String password;
	private String email;

	// Otros campos como email o nombre si son necesarios
	// Getters y setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
