package Integrador.Integrador.controller;

import java.sql.Date;
import java.util.List;

import Integrador.Integrador.dao.FacturaDAO;
import Integrador.Integrador.model.Factura;
import Integrador.Integrador.model.exceptions.FacturaExceptions;

public class FacturaController {
	private FacturaDAO facturaDAO;

	public FacturaController() {
		this.facturaDAO = new FacturaDAO();
	}

	public void agregarFactura(int numeroFactura, int idCliente, int idProducto, Date fecha, double total) throws FacturaExceptions {
		Factura factura = new Factura(numeroFactura, idCliente, idProducto, fecha, total);
		facturaDAO.crear(factura);
	}

	public List<Factura> listarFacturas() throws FacturaExceptions {
		return facturaDAO.listar();
	}

	public List<Factura> listarFacturasPorCliente(int idCliente) throws FacturaExceptions {
		return facturaDAO.listarPorCliente(idCliente);
	}
}
