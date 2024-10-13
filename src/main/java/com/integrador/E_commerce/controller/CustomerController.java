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

import com.integrador.E_commerce.model.Customer;
import com.integrador.E_commerce.model.service.CustomerService;

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Obtener todos los clientes (GET)
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Obtener un cliente por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo cliente (POST)
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveOrUpdateCustomer(customer);
    }

    // Actualizar un cliente (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Customer existingCustomer = customerService.getCustomerById(id);
        if (existingCustomer != null) {
            existingCustomer.setNombre(customerDetails.getNombre());
            existingCustomer.setEmail(customerDetails.getEmail());
            existingCustomer.setDireccion(customerDetails.getDireccion());
            return ResponseEntity.ok(customerService.saveOrUpdateCustomer(existingCustomer));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un cliente (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
