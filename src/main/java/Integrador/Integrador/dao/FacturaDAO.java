package Integrador.Integrador.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Integrador.Integrador.model.Factura;
import Integrador.Integrador.model.exceptions.DuplicateFacturaException;
import Integrador.Integrador.model.exceptions.FacturaExceptions;
import Integrador.Integrador.util.ConnDb;

public class FacturaDAO implements DAO<Factura> {

	private static final String INSERT_FACTURA_SQL = "INSERT INTO factura (numeroFactura, idCliente, idProducto,fecha, total) VALUES (?, ?, ?, ?, ?)";
	private static final String SELECT_ALL_FACTURAS = "SELECT * FROM factura";
	private static final String SELECT_FACTURAS_BY_CLIENTE = "SELECT * FROM factura WHERE idCliente = ?";
	private static final String FIND_FACTURA = "SELECT COUNT(*) FROM factura WHERE idFactura = ? OR numeroFactura = ?";

	@Override
	public void crear(Factura factura) throws FacturaExceptions {
		try (Connection conn = ConnDb.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_FACTURA_SQL)) {
			if(exists(factura.getIdCliente(),factura.getNumeroFactura())) {
				throw new DuplicateFacturaException("Esta factura ya esta registrada");
			}
			preparedStatement.setInt(1, factura.getNumeroFactura());
			preparedStatement.setInt(2, factura.getIdCliente());
			preparedStatement.setInt(3, factura.getIdProducto());
			preparedStatement.setDate(4, new java.sql.Date(factura.getFecha().getTime()));
			preparedStatement.setDouble(5, factura.getTotal());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new FacturaExceptions("Error al crear la factura: " + e.getMessage());
		}
	}

	@Override
	public List<Factura> listar() throws FacturaExceptions {
		List<Factura> facturas = new ArrayList<>();
		try (Connection conn = ConnDb.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_FACTURAS)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int numeroFactura = rs.getInt("numeroFactura");
				int idCliente = rs.getInt("idCliente");
				int idProducto = rs.getInt("idProducto");
				Date fecha = rs.getDate("fecha");
				double total = rs.getDouble("total");
				facturas.add(new Factura(numeroFactura, idCliente,  idProducto, fecha,  total));
			}
		} catch (SQLException e) {
			throw new FacturaExceptions("Error al listar las facturas: " + e.getMessage());
		}
		return facturas;
	}

	public List<Factura> listarPorCliente(int idCliente) throws FacturaExceptions {
		List<Factura> facturas = new ArrayList<>();
		try (Connection conn = ConnDb.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(SELECT_FACTURAS_BY_CLIENTE)) {
			preparedStatement.setInt(1, idCliente);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int numeroFactura = rs.getInt("numeroFactura");
				Date fecha = rs.getDate("fecha");
				double total = rs.getDouble("total");
				facturas.add(new Factura(numeroFactura, idCliente, fecha, total));
			}
		} catch (SQLException e) {
			throw new FacturaExceptions("Error al listar las facturas del cliente: " + e.getMessage());
		}
		return facturas;
	}

	private boolean exists(int idCliente, int numeroFactura) throws SQLException {
		try (Connection conn = ConnDb.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(FIND_FACTURA)) {
			preparedStatement.setInt(1, idCliente);
			preparedStatement.setInt(2, numeroFactura);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			return rs.getInt(1) > 0;
		}
	}
}
