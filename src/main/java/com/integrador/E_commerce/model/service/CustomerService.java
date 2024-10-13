package com.integrador.E_commerce.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.integrador.E_commerce.model.Customer;

import com.integrador.E_commerce.model.repository.CustomerRepository;

public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Obtener todos los productos
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Obtener un producto por ID
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    // Guardar o actualizar un producto
    public Customer saveOrUpdateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Eliminar un producto
    public void deleteCustomer(Long id) {
    	customerRepository.deleteById(id);
    }
}
