package com.integrador.E_commerce.model.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	private ProductoRepository productoRepository;

	public ProductoService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public void fetchProducts() {
	
	    ResponseEntity<List<Producto>> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Producto>>() {});
	    
	    List<Producto> productos = response.getBody();
	    System.out.println("Número de productos obtenidos: " + productos.size());
	    
	    if (productos != null) {
	        for (Producto producto : productos) {
	        	if (producto.getDescripcion().length() > 255) {
	                producto.setDescripcion(producto.getDescripcion().substring(0, 255));
	            }
	            productoRepository.save(producto);
	        }
	    }
	}


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
