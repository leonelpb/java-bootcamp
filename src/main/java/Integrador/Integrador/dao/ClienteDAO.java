package Integrador.Integrador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Integrador.Integrador.model.Cliente;
import Integrador.Integrador.model.exceptions.ClienteExceptions;
import Integrador.Integrador.model.exceptions.DuplicateClientException;
import Integrador.Integrador.util.ConnDb;

public class ClienteDAO implements DAO<Cliente> {
	private static final String INSERT_CLIENTE_SQL = "INSERT INTO cliente (idCliente,dni, nombreCliente, email, telefono) VALUES (?, ?, ?, ?, ?)";
	private static final String SELECT_ALL_CLIENTES = "SELECT * FROM cliente";
	private static final String DELETE_CLIENTE_SQL = "DELETE FROM cliente WHERE id = ?";
	private static final String UPDATE_CLIENTE_SQL = "UPDATE cliente SET nombre = ?, apellidos = ?, direccion = ?, telefono = ? WHERE id = ?";
	public static final String  FIND_CLIENTE = "SELECT COUNT(*) FROM cliente WHERE idCliente = ? OR dni = ?";

	@Override
	public void crear(Cliente cliente) throws ClienteExceptions {
		try (Connection conn = ConnDb.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_CLIENTE_SQL)) {
			if (exists (cliente.getIdCliente(), cliente.getDni())) {
				throw new DuplicateClientException("El cliente con este Id o DNI ya existe.");
			}
			preparedStatement.setInt(1, cliente.getIdCliente());
			preparedStatement.setString(2, cliente.getDni());
			preparedStatement.setString(3, cliente.getNombreCliente());
			preparedStatement.setString(4, cliente.getEmail());
			preparedStatement.setString(5, cliente.getTelefono());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new ClienteExceptions("Error al crear el cliente: " + e.getMessage());
		}
	}

	@Override
	public List<Cliente> listar() throws ClienteExceptions {
		List<Cliente> clientes = new ArrayList<>();
		try (Connection conn = ConnDb.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_CLIENTES)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int idCliente = rs.getInt("idCliente");
				String dni = rs.getString("dni");
				String nombreCliente = rs.getString("nombreCliente");
				String email = rs.getString("email");
				String telefono = rs.getString("telefono");
				clientes.add(new Cliente(idCliente, dni, nombreCliente, email, telefono));
			}
		} catch (SQLException e) {
			throw new ClienteExceptions("Error al listar los clientes: " + e.getMessage());
		}
		return clientes;
	}

	public void eliminar(int id) throws ClienteExceptions {
		try (Connection conn = ConnDb.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(DELETE_CLIENTE_SQL)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new ClienteExceptions("Error al eliminar el cliente: " + e.getMessage());
		}
	}

	public void modificar(Cliente cliente) throws ClienteExceptions {
		try (Connection connection = ConnDb.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENTE_SQL)) {
			preparedStatement.setString(1, cliente.getDni());
			preparedStatement.setString(2, cliente.getNombreCliente());
			preparedStatement.setString(3, cliente.getEmail());
			preparedStatement.setString(4, cliente.getTelefono());
			preparedStatement.setInt(5, cliente.getIdCliente());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new ClienteExceptions("Error al modificar el cliente: " + e.getMessage());
		}
	}

	private boolean exists(int idCliente, String dni) throws SQLException {
		try (Connection conn = ConnDb.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(FIND_CLIENTE)) {
			preparedStatement.setInt(1, idCliente);
			preparedStatement.setString(2, dni);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			return rs.getInt(1) > 0;
		}
	}
}
