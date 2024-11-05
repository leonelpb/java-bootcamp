package com.integrador.E_commerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	private Long customerId;
	private String nombre;
	private String username;
	private String email;
	private String password;
	private CartDTO cart;
	private long cartId;

}
