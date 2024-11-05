import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../core/models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {

  private apiUrl = "http://localhost:8080/api/v1/products/all";

  constructor(private http:HttpClient) { }
  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl);
  }

  // Añadir producto al carrito
  addToCart(cartId: number, productId: number, quantity: number): Observable<any> {
    const url = `${this.apiUrl}/public/carts/${cartId}/products/${productId}/quantity/${quantity}`;
    return this.http.put(url, {}); // Enviamos un objeto vacío porque no estamos pasando un body
  }

}
