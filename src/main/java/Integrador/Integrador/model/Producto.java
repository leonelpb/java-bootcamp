package Integrador.Integrador.model;

public class Producto {
	private int  idProducto;
	private String nombreProducto;
	private int stock;
	private double precio;
	private String descripcion;
	
	
	
	public Producto(int idProducto, String nombreProducto, int stock, double precio, String descripcion) {
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.stock = stock;
		this.precio = precio;
		this.descripcion = descripcion;
	}


	public int getIdProducto() {
		return idProducto;
	}



	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}



	public String getNombreProducto() {
		return nombreProducto;
	}



	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}



	public int getStock() {
		return stock;
	}



	public void setStock(int stock) {
		this.stock = stock;
	}



	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", stock=" + stock
				+ ", precio=" + precio + ", descripcion=" + descripcion + "]";
	}
	
	
}
