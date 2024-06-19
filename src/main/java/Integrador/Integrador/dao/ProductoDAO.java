package Integrador.Integrador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Integrador.Integrador.controller.ProductoController;
import Integrador.Integrador.model.Producto;
import Integrador.Integrador.model.exceptions.DuplicateProductException;
import Integrador.Integrador.model.exceptions.InvalidProductException;
import Integrador.Integrador.model.exceptions.ProductExceptions;
import Integrador.Integrador.util.ConnDb;

public class ProductoDAO implements DAO<Producto> {

	private static final String INSERT_PRODUCTO_SQL = "INSERT INTO producto (idProducto, nombreProducto, precio, stock, descripcion) VALUES (?, ?, ?, ?, ?)";
	private static final String SELECT_ALL_PRODUCTO = "SELECT * FROM producto";
	private static final String UPDATE_PRODUCTO_STOCK = "UPDATE producto SET stock = stock + ? WHERE idProducto = ?";
	public static final String  FIND_PRODUCT = "SELECT COUNT(*) FROM producto WHERE idProducto = ? OR nombreProducto = ?";

	@Override
	public void crear(Producto producto) throws SQLException, ProductExceptions {
		if (producto.getPrecio() <= 0 || producto.getStock() < 0) {
			throw new InvalidProductException("El precio debe ser mayor a 0 y el stock no puede ser negativo.");
		}
		try (Connection conn = ConnDb.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_PRODUCTO_SQL)) {
			if (exists(producto.getIdProducto(), producto.getNombreProducto())) {
				throw new DuplicateProductException("El producto con este ID o nombre ya existe.");
			}
			preparedStatement.setInt(1, producto.getIdProducto());
			preparedStatement.setString(2, producto.getNombreProducto());
			preparedStatement.setDouble(3, producto.getPrecio());
			preparedStatement.setInt(4, producto.getStock());
			preparedStatement.setString(5, producto.getDescripcion());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new ProductExceptions("Error al crear el producto" + e.getMessage());
		}
	}

	@Override
	public List<Producto> listar() throws ProductExceptions {
		List<Producto> productos = new ArrayList<>();
		try (Connection conn = ConnDb.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_PRODUCTO)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int idProducto = rs.getInt("idProducto");
				String nombreProducto = rs.getString("nombreProducto");
				int stock = rs.getInt("stock");
				double precio = rs.getDouble("precio");
				String descripcion = rs.getString("descripcion");
				productos.add(new Producto(idProducto, nombreProducto, stock, precio, descripcion));
			}
		} catch (SQLException e) {
			throw new ProductExceptions("Error al listar los productos: " + e.getMessage());
		}
		return productos;

	}

	public void modificarStock(int id, int cantidad) throws SQLException, InvalidProductException {
		if (cantidad <= 0) {
			throw new InvalidProductException("La cantidad a añadir debe ser mayor que 0.");
		}
		try (Connection conn = ConnDb.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_PRODUCTO_STOCK)) {
			preparedStatement.setInt(1, cantidad);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		}
	}

	private boolean exists(int idProducto, String nombreProducto) throws SQLException {
		try (Connection connection = ConnDb.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT)) {
			preparedStatement.setInt(1, idProducto);
			preparedStatement.setString(2, nombreProducto);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			return rs.getInt(1) > 0;
		}
	}

}
