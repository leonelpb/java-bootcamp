package com.integrador.E_commerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Security {

	private final UserDetailsService userDetailsService;

	public Security(JwtRequestFilter jwtRequestFilter, UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // Deshabilitamos CSRF de la nueva manera
				.authorizeHttpRequests(auth -> auth.requestMatchers("/login", "/signup", "/products").permitAll()
						.anyRequest().authenticated())
				.httpBasic(httpBasic -> {
				}); // Mantenemos la autenticación básica o el tipo que hayas definido

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		
		  AuthenticationManagerBuilder authenticationManagerBuilder = 
	                http.getSharedObject(AuthenticationManagerBuilder.class);
	        
	        authenticationManagerBuilder.userDetailsService(userDetailsService)
	                .passwordEncoder(passwordEncoder());
	        
	        return authenticationManagerBuilder.build(); // Construir y devolver el AuthenticationManager
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
