import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from '../../Services/product-service.service';
import { CommonModule } from '@angular/common';
import { Product } from '../../core/models/product.model';
import { CartService } from '../../Services/cart.service';
import { AuthService } from '../../core/services/auth.service';



@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.scss'
})
export class ProductListComponent implements OnInit {
isAuthenticated: boolean=false;

  products: Product[] = [];
cart: any;

  constructor(private productService: ProductServiceService,
    private cartService: CartService,
    private authService:AuthService
   ) {}

  ngOnInit(): void {
    this.isAuthenticated = this.authService.isAuthenticated();
    this.productService.getProducts().subscribe(products => {
      this.products = products;
    });
  }


  addToCart(productId: number, quantity: number) {
    const cartId = this.authService.getCartId(); // Obtener el cartId
    if (cartId) {
      this.productService.addToCart(cartId, productId, quantity).subscribe({
        next: (response) => {
          console.log('Producto agregado al carrito:', response);
        },
        error: (error) => {
          console.error('Error al agregar producto al carrito:', error);
        }
      });
    } else {
      console.error('No se ha encontrado el cartId. Asegúrate de iniciar sesión.');
    }
  }
}
