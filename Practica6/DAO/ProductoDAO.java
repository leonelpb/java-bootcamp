package Practica6.Practica6.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Practica6.Practica6.modelo.Producto;
import Practica6.Practica6.util.ConexionDb;

public class ProductoDAO implements DAO<Producto> {

	@Override
	public void crear(Producto producto) {
		String query = "INSERT INTO Producto (nombreProducto,stock, precio) VALUES (?,?,?)";
		try (Connection conexion = ConexionDb.getConnection();
				PreparedStatement stmt = conexion.prepareStatement(query)) {
			stmt.setString(1, producto.getNombreProducto());
			stmt.setInt(2, producto.getStock());
			stmt.setDouble(3, producto.getPrecio());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Producto> listar() {
		List<Producto> productos = new ArrayList<>();
		String query = " SELECT *FROM Producto";
		try (Connection conexion = ConexionDb.getConnection();
				Statement stmt = conexion.createStatement();
				ResultSet rs = stmt.executeQuery(query);
			){
			while(rs.next()) {
				int idProducto = rs.getInt("idProducto");
				String nombreProducto = rs.getString("nombreProducto");
				double precio = rs.getDouble("precio");
				int stock = rs.getInt("stock");
				productos.add(new Producto(idProducto, nombreProducto, stock, precio));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return productos;
	}

}
