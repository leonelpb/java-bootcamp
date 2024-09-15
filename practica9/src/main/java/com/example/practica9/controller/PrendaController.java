package com.example.practica9.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.practica9.model.Prenda;
import com.example.practica9.service.RopaService;

@RestController
@RequestMapping(path = "api/v1/prendas")

public class PrendaController {

	@Autowired
	private RopaService ropaService; 
	
	@GetMapping
	public List<Prenda> getAll(){
		return ropaService.getPrendas();
	}
	
	@PostMapping
	public void saveUpdate(@RequestBody Prenda prenda) {
		ropaService.saveOrUpdate(prenda);
	}
	
}
