import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartService } from '../../Services/cart.service';
import { CartDTO } from '../../core/models/cart.model';
import { ProductServiceService } from '../../Services/product-service.service';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})
export class CartComponent {
  @Input() isCartOpen: boolean = false;
  @Output() closeCart = new EventEmitter<void>;

  cart: CartDTO = {
    products: [],
    id: 0,
    userId: 0,
    totalAmount: 0
  };
  cartId!: number;
  subtotal: number = 0;

  constructor(private cartService: CartService, private productsService: ProductServiceService, private authService: AuthService) { }

  ngOnInit(): void {
    this.getCart();
  }
  getCart(): void {
    const cartId = this.authService.getCartId();
    const validCartId = cartId ? cartId : 1;
    this.cartService.getCartById(validCartId).subscribe({
      next: (cart: CartDTO) => {
        this.cart = cart;
        console.log('Carrito obtenido:', cart);
        this.calculateSubtotal();
      },
      error: (error) => {
        console.error('Error al obtener el carrito:', error);
      },
      complete: () => {
        console.log('La solicitud de carrito ha finalizado');
      }
    });
  }

  calculateSubtotal(): void {
    this.subtotal = this.cart.products.reduce((total, product) => total + (product.price * product.quantity), 0);
  }

  // Método para agregar un producto al carrito
  addProduct(cartId: number, productId: number, quantity: number) {
    this.cartService.addProductToCart(cartId, productId, quantity).subscribe(cart => {
      this.cart = cart;
      console.log('Producto añadido al carrito:', cart);
    });
  }

  // Método para actualizar la cantidad de un producto en el carrito
  updateProductQuantity(cartId: number, productId: number, quantity: number) {
    this.cartService.updateCartProduct(cartId, productId, quantity).subscribe(cart => {
      this.cart = cart;
      console.log('Cantidad actualizada:', cart);
    });
  }

  // Método para eliminar un producto del carrito
  deleteProduct(cartId: number, productId: number) {
    this.cartService.deleteProductFromCart(cartId, productId).subscribe(response => {
      console.log(response);
    });
  }


  onClose() {
    this.closeCart.emit();
  }
}
