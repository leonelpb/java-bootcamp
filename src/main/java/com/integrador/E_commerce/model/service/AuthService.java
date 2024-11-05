package com.integrador.E_commerce.model.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.integrador.E_commerce.DTO.CartDTO;
import com.integrador.E_commerce.DTO.CustomerDTO;
import com.integrador.E_commerce.DTO.LoginRequest;
import com.integrador.E_commerce.DTO.SignupRequest;
import com.integrador.E_commerce.config.JwtUtil;
import com.integrador.E_commerce.model.Cart;
import com.integrador.E_commerce.model.Customer;
import com.integrador.E_commerce.model.repository.CartRepository;
import com.integrador.E_commerce.model.repository.CustomerRepository;

@Service
public class AuthService {
	private final CustomerRepository customerRepository;
	private final CartRepository cartRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	@Autowired
	public AuthService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, CartRepository cartRepository) {
		this.customerRepository = customerRepository;
		this.cartRepository = cartRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	public Map<String, Object> login(LoginRequest loginRequest) {
		Optional<Customer> customerOpt = customerRepository.findByUsername(loginRequest.getUsername());

		if (customerOpt.isPresent()) {
			Customer customer = customerOpt.get();
			if (passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword())) {
				String token = jwtUtil.generateToken(customer.getUsername());
				CustomerDTO customerDTO = new CustomerDTO();
	            customerDTO.setCustomerId(customer.getCustomerId());
	            customerDTO.setUsername(customer.getUsername());

	            // Check if the customer has an associated cart
	            if (customer.getCart() != null) {
	                customerDTO.setCartId(customer.getCart().getCartId());
	            } else {
	                customerDTO.setCart(null);  // Set cartId as null if no cart is associated
	            }
	            // Crear el Map para contener el token y el CustomerDTO
	            Map<String, Object> response = new HashMap<>();
	            response.put("token", token);
	            response.put("customer", customerDTO);

	            return response;
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
		Cart cart = new Cart();
		Customer newCustomer = new Customer();
		newCustomer.setNombre(signupRequest.getNombre());	
		newCustomer.setUsername(signupRequest.getUsername());
		newCustomer.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		newCustomer.setCart(cart);
		newCustomer.setEmail(signupRequest.getEmail()); // Si tienes un campo email en Customer

		// Guardamos el nuevo usuario
		customerRepository.save(newCustomer);
		cart.setCustomer(newCustomer);
		cartRepository.save(cart);

		// Retornamos el token JWT
		return jwtUtil.generateToken(newCustomer.getUsername());
	}
}
