package com.integrador.E_commerce.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO {
	private Long productId;
	private String nombre;
	private String imagen;
	private String descripcion;
	private Integer stock;
	private double precio;
}
