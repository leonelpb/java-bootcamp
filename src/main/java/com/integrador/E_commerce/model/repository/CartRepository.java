package com.integrador.E_commerce.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.integrador.E_commerce.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
	
	@Query("SELECT c FROM Cart c WHERE c.customer.email = ?1 AND c.id = ?2")
	Cart findCartByEmailAndCartId(String email, Long cartId);

	@Query("SELECT c FROM Cart c JOIN FETCH c.cartItems ci JOIN FETCH ci.producto p WHERE p.id = ?1")
	List<Cart> findCartsByProductId(Long productId);
}
