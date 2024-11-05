package com.integrador.E_commerce.model.service;

import java.util.List;

import com.integrador.E_commerce.DTO.CartDTO;

public interface CartService {
CartDTO addProductToCart(Long cartId, Long productId, Integer quantity);
	
	List<CartDTO> getAllCarts();
	
	CartDTO getCart(Long cartId);
	
	CartDTO updateProductQuantityInCart(Long cartId, Long productId, Integer quantity);
	
	void updateProductInCarts(Long cartId, Long productId);
	
	String deleteProductFromCart(Long cartId, Long productId);
}
