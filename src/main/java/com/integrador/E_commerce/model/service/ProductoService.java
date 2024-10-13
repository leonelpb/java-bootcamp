package com.integrador.E_commerce.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.integrador.E_commerce.model.Producto;
import com.integrador.E_commerce.model.repository.ProductoRepository;

public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    public List<Producto> getAllProducts() {
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
