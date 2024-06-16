package Practica6.Practica6.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Practica6.Practica6.modelo.Cliente;
import Practica6.Practica6.util.ConexionDb;

public class ClienteDAO implements DAO<Cliente> {

	@Override
	public void crear(Cliente cliente) {
		String query = "INSERT INTO Cliente (dni, nombreCliente, email, telefono) values(?,?,?,?)";
		try(Connection conexion = ConexionDb.getConnection();
				PreparedStatement stmt = conexion.prepareStatement(query)){
			stmt.setString(1, cliente.getDni());
			stmt.setString(2, cliente.getNombreCliente());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getTelefono());
			stmt.executeUpdate();
			}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Cliente> listar() {
		List<Cliente> clientes = new ArrayList<>();
		String query = "SELECT *FROM Cliente"; 
		try(Connection conexion = ConexionDb.getConnection();
				Statement stmt = conexion.createStatement();
				ResultSet rs = stmt.executeQuery(query)){
			String dni = rs.getString("dni");
			String nombreCliente = rs.getString("nombreCliente");
			String email = rs.getString("email");
			String telefono = rs.getString("telefono");
			int idCliente = rs.getInt("idCliente");
			clientes.add(new Cliente(idCliente, dni,nombreCliente, email, telefono));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

}
