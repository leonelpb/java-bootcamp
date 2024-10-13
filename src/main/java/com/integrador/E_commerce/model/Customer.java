package com.integrador.E_commerce.model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    private String direccion;
    
    private String username;
    
    private String password;

    // Un cliente puede tener varios carritos (si decides manejar varios), por ahora uno
    @OneToMany(mappedBy = "customer")
    private List<Cart> carts;

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	public void setPassword(String encode) {
		this.password = encode;
		
	}

	public void setUsername(String username) {
		this.username = username;
		
	}
	
}