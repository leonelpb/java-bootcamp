package com.integrador.E_commerce.model.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.integrador.E_commerce.model.Cart;
import com.integrador.E_commerce.model.Customer;
import com.integrador.E_commerce.model.Producto;
import com.integrador.E_commerce.model.repository.ProductoRepository;

@Service
public class ProductoService {

	private final String apiUrl = "https://fakestoreapi.com/products";

	private final RestTemplate restTemplate;

	public ProductoService(RestTemplate restTemplate) {
	        this.restTemplate = restTemplate;
	    }
	
	public List<Producto> fetchProducts() {
        try {
            Producto[] productos = restTemplate.getForObject(apiUrl, Producto[].class);
            return Arrays.asList(productos);
        } catch (Exception e) {
            // Manejo básico de excepciones en caso de error en la API
            System.err.println("Error al obtener productos de la API: " + e.getMessage());
            return List.of(); // Devuelve una lista vacía en caso de error
        }
    }
	  public void addProductToCart(Cart cart, Producto producto) {
	        cart.getCart.add(producto);
	    }

	@Autowired
	private ProductoRepository productoRepository;

	// Obtener todos los productos
	public List<Producto> getAllProducts() {
		fetchProducts();
		return productoRepository.findAll();
	}

	// Obtener un producto por ID
	public Producto getProductoById(Long id) {
		return productoRepository.findById(id).orElse(null);
	}

	// Guardar o actualizar un producto
	public Producto saveOrUpdateProduct(Producto producto) {
		return productoRepository.save(producto);
	}

	// Eliminar un producto
	public void deleteProducto(Long id) {
		productoRepository.deleteById(id);
	}
}
