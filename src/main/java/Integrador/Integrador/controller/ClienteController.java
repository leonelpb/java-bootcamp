package Integrador.Integrador.controller;

import java.util.List;

import Integrador.Integrador.dao.ClienteDAO;
import Integrador.Integrador.model.Cliente;
import Integrador.Integrador.model.exceptions.ClienteExceptions;

public class ClienteController {
	private ClienteDAO clienteDAO;

	public ClienteController() {
		this.clienteDAO = new ClienteDAO();
	}

	public void agregarCliente(int idCliente, String dni, String nombreCliente, String email, String telefono)
			throws ClienteExceptions {
		Cliente cliente = new Cliente(idCliente, dni, nombreCliente, email, telefono);
		clienteDAO.crear(cliente);
	}

	public List<Cliente> listarClientes() throws ClienteExceptions {
		return clienteDAO.listar();
	}

	public void eliminarCliente(int id) throws ClienteExceptions {
		clienteDAO.eliminar(id);
	}

	public void modificarCliente(int idCliente, String dni, String nombreCliente, String email, String telefono)
			throws ClienteExceptions {
		Cliente cliente = new Cliente(idCliente, dni, nombreCliente, email, telefono);
		clienteDAO.modificar(cliente);
	}
}
