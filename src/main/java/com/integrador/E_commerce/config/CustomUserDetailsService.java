package com.integrador.E_commerce.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;


import com.integrador.E_commerce.model.Customer;
import com.integrador.E_commerce.model.repository.CustomerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository; // Repositorio de clientes

	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        // Aquí buscas un cliente por su nombre de usuario
	        Optional<Customer> customerOpt = customerRepository.findByUsername(username); // Usa el modelo Customer

	        // Si el cliente no se encuentra, lanza una excepción
	        Customer customer = customerOpt.orElseThrow(() -> 
            new UsernameNotFoundException("User not found with username: " + username)
        );

	        // Retorna un nuevo objeto CustomerUserDetails con la información del cliente
	        return new CustomerUserDetails(customer.getUsername(), customer.getPassword(), customer.getAuthorities());
	    }
}
