import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environments } from './../../environments/environments'; // Asegúrate de tener la URL base en tus variables de entorno
import { CartDTO } from '../core/models/cart.model'; // Crea esta interfaz para tu modelo de datos de carrito

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private baseUrl = `${environments.apiUrl}/api/v1/carts`; // URL base del controlador en el backend

  constructor(private http: HttpClient) { }

  // Método para añadir un producto al carrito
  addProductToCart(cartId: number, productId: number, quantity: number): Observable<CartDTO> {
    const url = `${this.baseUrl}/public/carts/${cartId}/products/${productId}/quantity/${quantity}`;
    return this.http.post<CartDTO>(url, {});
  }

  // Método para obtener todos los carritos (requiere permisos de admin)
  getCarts(): Observable<CartDTO[]> {
    const url = `${this.baseUrl}/admin/carts`;
    return this.http.get<CartDTO[]>(url);
  }

  // Método para otener un carrito por ID de usuario y carrito
  getCartById(cartId: number): Observable<CartDTO> {
    const url = `${this.baseUrl}/public/carts/${cartId}`;
    return this.http.get<CartDTO>(url);
  }

  // Método para actualizar la cantidad de un producto en el carrito
  updateCartProduct(cartId: number, productId: number, quantity: number): Observable<CartDTO> {
    const url = `${this.baseUrl}/public/carts/${cartId}/products/${productId}/quantity/${quantity}`;
    return this.http.put<CartDTO>(url, {});
  }

  // Método para eliminar un producto del carrito
  deleteProductFromCart(cartId: number, productId: number): Observable<string> {
    const url = `${this.baseUrl}/public/carts/${cartId}/product/${productId}`;
    return this.http.delete<string>(url);
  }
}
