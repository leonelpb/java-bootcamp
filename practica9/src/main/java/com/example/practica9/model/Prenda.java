package com.example.practica9.model;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity

@Table(name= "tbl_prenda")
public class Prenda {
   
	@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String talla;
	
	private String color;
	
	private Double precio;
	
	public Prenda () {}

	public Prenda(String talla, String color, Double precio) {
		super();
		this.talla = talla;
		this.color = color;
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
   
}
