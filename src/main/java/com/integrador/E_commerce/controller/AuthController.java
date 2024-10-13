package com.integrador.E_commerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrador.E_commerce.DTO.AuthResponse;
import com.integrador.E_commerce.DTO.LoginRequest;
import com.integrador.E_commerce.DTO.SignupRequest;
import com.integrador.E_commerce.model.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	// Endpoint para login
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
		String token = authService.login(loginRequest);
		return ResponseEntity.ok(new AuthResponse(token));
	}

	// Endpoint para signup
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
		authService.signup(signupRequest);
		return ResponseEntity.ok("User registered successfully!");
	}
}
