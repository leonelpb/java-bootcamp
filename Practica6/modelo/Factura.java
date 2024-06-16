package Practica6.Practica6.modelo;

import java.sql.Date;

public class Factura {
	private int numeroFactura;
	private int idCliente;
	private int idProducto;
	private Date fecha;
	private int cantidad;
	private double total;
	
	
	public Factura(int numeroFactura, double total, int idCliente, int idProducto) {
		this.numeroFactura = numeroFactura;
		this.total = total;
		this.idCliente = idCliente;
		this.idProducto = idProducto;
		}
	
	public Factura(int numeroFactura, int idCliente, int idProducto, Date fecha, int cantidad, double total) {
		this.numeroFactura = numeroFactura;
		this.idCliente = idCliente;
		this.idProducto = idProducto;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.total = total;
	}
	
	public int getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(int numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Factura [numeroFactura=" + numeroFactura + ", idCliente=" + idCliente + ", idProducto=" + idProducto
				+ ", fecha=" + fecha + ", cantidad=" + cantidad + ", total=" + total + "]";
	}
	
	
}
