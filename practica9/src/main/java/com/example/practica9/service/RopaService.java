package com.example.practica9.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.practica9.model.Prenda;
import com.example.practica9.repository.RopaRepository;

@Service
public class RopaService {
	
	@Autowired
	
	RopaRepository ropaRepository;
	
	public List<Prenda> getPrendas(){
		return ropaRepository.findAll();
	}
	
	public void saveOrUpdate(Prenda prenda) {
		ropaRepository.save(prenda);
	}
}
