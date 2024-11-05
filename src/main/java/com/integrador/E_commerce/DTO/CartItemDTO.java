package com.integrador.E_commerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
	private Long cartItemId;
	private CartDTO cart;
	private ProductDTO product;
	private Integer stock;
	private double precio;
}
