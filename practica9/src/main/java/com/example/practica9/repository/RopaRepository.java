package com.example.practica9.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practica9.model.Prenda;

@Repository

public interface RopaRepository extends JpaRepository <Prenda, Long> {

}
