package Integrador.Integrador.model;

import java.sql.Date;

public class Factura {
	private int numeroFactura;
	private int idCliente;
	private int idProducto;
	private Date fecha;
	private double total;
	
	
	
	public Factura(int numeroFactura, int idCliente, int idProducto, Date fecha, double total) {
		super();
		this.numeroFactura = numeroFactura;
		this.idCliente = idCliente;
		this.idProducto = idProducto;
		this.fecha = fecha;
		this.total = total;
	}


	public Factura(int numeroFactura, int idCliente, Date fecha, double total) {
		super();
		this.numeroFactura = numeroFactura;
		this.idCliente = idCliente;
		this.fecha = fecha;
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



	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	@Override
	public String toString() {
		return "Factura [numeroFactura=" + numeroFactura + ", idCliente=" + idCliente + ", idProducto=" + idProducto
				+ ", fecha=" + fecha + ", total=" + total + "]";
	}
	
	
}
