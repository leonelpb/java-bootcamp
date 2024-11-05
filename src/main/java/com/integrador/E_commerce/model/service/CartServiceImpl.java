package com.integrador.E_commerce.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.E_commerce.DTO.CartDTO;
import com.integrador.E_commerce.DTO.ProductDTO;
import com.integrador.E_commerce.Exceptions.APIException;
import com.integrador.E_commerce.Exceptions.ResourceNotFoundException;
import com.integrador.E_commerce.model.Cart;
import com.integrador.E_commerce.model.CartItem;
import com.integrador.E_commerce.model.Producto;
import com.integrador.E_commerce.model.repository.CartItemRepository;
import com.integrador.E_commerce.model.repository.CartRepository;
import com.integrador.E_commerce.model.repository.ProductoRepository;
import org.modelmapper.ModelMapper;

import jakarta.transaction.Transactional;


@Transactional
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private ProductoRepository productRepo;

	@Autowired
	private CartItemRepository cartItemRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CartDTO addProductToCart(Long cartId, Long productId, Integer quantity) {

		Cart cart = cartRepo.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

		Producto producto = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

		CartItem cartItem = cartItemRepo.findCartItemByProductIdAndCartId(cartId, productId);

		if (cartItem != null) {
			throw new APIException("Product " + producto.getNombre() + " already exists in the cart");
		}

		if (producto.getStock() == 0) {
			throw new APIException(producto.getNombre() + " is not available");
		}

		if (producto.getStock() < quantity) {
			throw new APIException("Please, make an order of the " + producto.getNombre()
					+ " less than or equal to the quantity " + producto.getStock() + ".");
		}

		CartItem newCartItem = new CartItem();

		newCartItem.setProducto(producto);
		newCartItem.setCart(cart);
		newCartItem.setStock(quantity);


		cartItemRepo.save(newCartItem);

		producto.setStock(producto.getStock() - quantity);

		cart.setTotalPrice(cart.getTotalPrice() * quantity);

		CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

		List<ProductDTO> productDTOs = cart.getCartItems().stream()
				.map(p -> modelMapper.map(p.getProducto(), ProductDTO.class)).collect(Collectors.toList());

		cartDTO.setProducts(productDTOs);

		return cartDTO;

	}

	@Override
	public List<CartDTO> getAllCarts() {
		List<Cart> carts = cartRepo.findAll();

		if (carts.size() == 0) {
			throw new APIException("No cart exists");
		}

		List<CartDTO> cartDTOs = carts.stream().map(cart -> {
			CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

			List<ProductDTO> products = cart.getCartItems().stream()
					.map(p -> modelMapper.map(p.getProducto(), ProductDTO.class)).collect(Collectors.toList());

			cartDTO.setProducts(products);

			return cartDTO;

		}).collect(Collectors.toList());

		return cartDTOs;
	}

	@Override
	public CartDTO getCart(String emailId, Long cartId) {
		Cart cart = cartRepo.findCartByEmailAndCartId(emailId, cartId);

		if (cart == null) {
			throw new ResourceNotFoundException("Cart", "cartId", cartId);
		}

		CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
		
		List<ProductDTO> products = cart.getCartItems().stream()
				.map(p -> modelMapper.map(p.getProducto(), ProductDTO.class)).collect(Collectors.toList());

		cartDTO.setProducts(products);

		return cartDTO;
	}
	
	@Override
	public CartDTO updateProductQuantityInCart(Long cartId, Long productId, Integer quantity) {
		Cart cart = cartRepo.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

		Producto producto = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

		if (producto.getStock() == 0) {
			throw new APIException(producto.getNombre() + " is not available");
		}

		if (producto.getStock() < quantity) {
			throw new APIException("Please, make an order of the " + producto.getNombre()
					+ " less than or equal to the quantity " + producto.getStock() + ".");
		}

		CartItem cartItem = cartItemRepo.findCartItemByProductIdAndCartId(cartId, productId);

		if (cartItem == null) {
			throw new APIException("Product " + producto.getNombre() + " not available in the cart!!!");
		}

		double cartPrice = cart.getTotalPrice() - (cartItem.getPrecio() * cartItem.getStock());

		producto.setStock(producto.getStock() + cartItem.getStock() - quantity);


		cartItem.setStock(quantity);


		cart.setTotalPrice(cartPrice + (cartItem.getPrecio() * quantity));

		cartItem = cartItemRepo.save(cartItem);

		CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

		List<ProductDTO> productDTOs = cart.getCartItems().stream()
				.map(p -> modelMapper.map(p.getProducto(), ProductDTO.class)).collect(Collectors.toList());

		cartDTO.setProducts(productDTOs);

		return cartDTO;

	}


	@Override
	public String deleteProductFromCart(Long cartId, Long productId) {
		Cart cart = cartRepo.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

		CartItem cartItem = cartItemRepo.findCartItemByProductIdAndCartId(cartId, productId);

		if (cartItem == null) {
			throw new ResourceNotFoundException("Producto", "productId", productId);
		}

		cart.setTotalPrice(cart.getTotalPrice() - (cartItem.getPrecio() * cartItem.getStock()));

		Producto producto = cartItem.getProducto();
		producto.setStock(producto.getStock() + cartItem.getStock());

		cartItemRepo.deleteCartItemByProductIdAndCartId(cartId, productId);

		return "Product " + cartItem.getProducto().getNombre() + " removed from the cart !!!";
	}

	@Override
	public void updateProductInCarts(Long cartId, Long productId) {
		// TODO Auto-generated method stub
		
	}

}
