package Practica6.Practica6.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Practica6.Practica6.modelo.Factura;
import Practica6.Practica6.util.ConexionDb;

public class FacturaDAO implements DAO<Factura>{

	@Override
	public void crear(Factura factura) {
		String query = "INSERT INTO Factura (numeroFactura, idCliente, idProducto, fecha, cantidad, total) values(?,?,?,?,?,?)";
		try(Connection conexion = ConexionDb.getConnection();
				PreparedStatement stmt = conexion.prepareStatement(query);
				){
			stmt.setInt(1, factura.getNumeroFactura());
			stmt.setInt(2, factura.getIdCliente());
			stmt.setInt(3, factura.getIdProducto());
			stmt.setDate(4, factura.getFecha());
			stmt.setInt(5, factura.getCantidad());
			stmt.setDouble(6, factura.getTotal());
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Factura> listar() {
		List<Factura> facturas = new ArrayList<>();
		String query = "SELECT *FROM Factura";
		try(Connection conexion = ConexionDb.getConnection();
				Statement stmt = conexion.createStatement();
				ResultSet rs = stmt.executeQuery(query)){
			while(rs.next()) {
				int numeroFactura = rs.getInt("numeroFactura");
				int idCliente =  rs.getInt("idCliente");
				int idProducto =  rs.getInt("idProducto");
				int cantidad=  rs.getInt("cantidad");
				Date fecha = rs.getDate("fecha");
				double total = rs.getDouble("total");
				
				facturas.add(new Factura(numeroFactura, idCliente, idProducto, fecha, cantidad, total));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return facturas;
	}

}
