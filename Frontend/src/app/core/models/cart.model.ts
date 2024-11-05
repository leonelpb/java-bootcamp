export interface CartDTO {
  id: number;
  userId: number;
  products: Array<{
    productId: number;
    name: string;
    image:string;
    quantity: number;
    price: number;
  }>;
  totalAmount: number;
}
