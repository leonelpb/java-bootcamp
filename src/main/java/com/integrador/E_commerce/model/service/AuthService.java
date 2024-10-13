package com.integrador.E_commerce.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.integrador.E_commerce.DTO.LoginRequest;
import com.integrador.E_commerce.DTO.SignupRequest;
import com.integrador.E_commerce.config.JwtUtil;
import com.integrador.E_commerce.model.Customer;
import com.integrador.E_commerce.model.repository.CustomerRepository;

@Service
public class AuthService {
	private final CustomerRepository customerRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	@Autowired
	public AuthService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.customerRepository = customerRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	public String login(LoginRequest loginRequest) {
		Optional<Customer> customerOpt = customerRepository.findByUsername(loginRequest.getUsername());

		if (customerOpt.isPresent()) {
			Customer customer = customerOpt.get();
			if (passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword())) {
				// Si las credenciales son correctas, generamos el token
				return jwtUtil.generateToken(customer.getUsername());
			}
		}
		throw new RuntimeException("Invalid username or password");
	}

	public String signup(SignupRequest signupRequest) {
		// Verificamos si el usuario ya existe
		if (customerRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
			throw new RuntimeException("User already exists");
		}

		// Creamos un nuevo cliente usando el DTO
		Customer newCustomer = new Customer();
		newCustomer.setUsername(signupRequest.getUsername());
		newCustomer.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		newCustomer.setEmail(signupRequest.getEmail()); // Si tienes un campo email en Customer

		// Guardamos el nuevo usuario
		customerRepository.save(newCustomer);

		// Retornamos el token JWT
		return jwtUtil.generateToken(newCustomer.getUsername());
	}
}
