package com.integrador.E_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrador.E_commerce.model.Cart;
import com.integrador.E_commerce.model.Producto;
import com.integrador.E_commerce.model.service.CartService;
import com.integrador.E_commerce.model.service.ProductoService;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductoService productService;

    // Obtener todos los carritos (GET)
    @GetMapping("/cart")
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }
    
    @PostMapping("/add")
    public void addToCart(@RequestBody Producto producto) {
        cartService.addToCart(producto);
    }
    
    @GetMapping
    public List<Producto> getCartItems() {
        return cartService.getCartItems();
    }

    // Obtener un carrito por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Cart cart = cartService.getCartById(id);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo carrito (POST)
    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.saveOrUpdateCart(cart);
    }

    // Eliminar un carrito (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        Cart cart = cartService.getCartById(id);
        if (cart != null) {
            cartService.deleteCart(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
