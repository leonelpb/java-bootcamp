package com.integrador.E_commerce.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrador.E_commerce.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
}
