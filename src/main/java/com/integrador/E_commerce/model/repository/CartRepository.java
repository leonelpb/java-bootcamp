package com.integrador.E_commerce.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrador.E_commerce.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

}
