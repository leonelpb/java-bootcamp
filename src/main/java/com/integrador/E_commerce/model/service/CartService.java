package com.integrador.E_commerce.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.E_commerce.model.repository.CartRepository;

import com.integrador.E_commerce.model.Cart;
import com.integrador.E_commerce.model.Producto;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    
    
    private List<Producto> cartItems = new ArrayList<>();

    public void addToCart(Producto producto) {
        cartItems.add(producto);
    }

    public List<Producto> getCartItems() {
        return cartItems;
    }

    // Obtener todos los carritos
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    // Obtener un carrito por ID
    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    // Guardar o actualizar un carrito
    public Cart saveOrUpdateCart(Cart cart) {
        return cartRepository.save(cart);
    }

    // Eliminar un carrito
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
