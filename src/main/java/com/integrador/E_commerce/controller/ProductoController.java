package com.integrador.E_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrador.E_commerce.model.Producto;
import com.integrador.E_commerce.model.service.ProductoService;

@RestController
@RequestMapping("/api/products")

public class ProductoController {
	@Autowired
    private ProductoService productoService;

    // Obtener todos los productos (GET)
    @GetMapping
    public List<Producto> getAllProducts() {
        return productoService.getAllProducts();
    }

    // Obtener un producto por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductById(@PathVariable Long id) {
        Producto producto = productoService.getProductoById(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo producto (POST)
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoService.saveOrUpdateProduct(producto);
    }

    // Actualizar un producto (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
        Producto existingProducto = productoService.getProductoById(id);
        if (existingProducto != null) {
            existingProducto.setNombre(productoDetails.getNombre());
            existingProducto.setDescripcion(productoDetails.getDescripcion());
            existingProducto.setPrecio(productoDetails.getPrecio());
            existingProducto.setStock(productoDetails.getStock());
            return ResponseEntity.ok(productoService.saveOrUpdateProduct(existingProducto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un producto (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Producto product = productoService.getProductoById(id);
        if (product != null) {
            productoService.deleteProducto(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
