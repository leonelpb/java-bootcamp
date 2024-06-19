package Integrador.Integrador.view;

import java.util.Scanner;

import Integrador.Integrador.controller.ClienteController;
import Integrador.Integrador.model.exceptions.ClienteExceptions;

public class VistaClientes {
	private ClienteController clienteController;
	private Scanner teclado;

	public VistaClientes(ClienteController clienteController, Scanner teclado) {
		this.clienteController = clienteController;
		this.teclado = teclado;
	}

	public void mostrarMenuClientes() {
		while (true) {
			System.out.println("Seleccione una opción:");
			System.out.println("1. Agregar cliente");
			System.out.println("2. Listar clientes");
			System.out.println("3. Eliminar cliente");
			System.out.println("4. Modificar cliente");
			System.out.println("5. Volver al menú principal");

			int opcion = Integer.parseInt(teclado.nextLine());

			switch (opcion) {
			case 1:
				agregarCliente();
				break;
			case 2:
				listarClientes();
				break;
			case 3:
				eliminarCliente();
				break;
			case 4:
				modificarCliente();
				break;
			case 5:
				return;
			default:
				System.out.println("Opción inválida, intente nuevamente.");
			}
		}
	}

	private void agregarCliente() {
		try {
			System.out.print("Ingrese el ID del cliente: ");
			int idCliente = Integer.parseInt(teclado.nextLine());

			System.out.print("Ingrese el DNI del cliente: ");
			String dni = teclado.nextLine();

			System.out.print("Ingrese nombre del cliente: ");
			String nombreCliente = teclado.nextLine();

			System.out.print("Ingrese el email del cliente: ");
			String email = teclado.nextLine();

			System.out.print("Ingrese el teléfono del cliente: ");
			String telefono = teclado.nextLine();

			clienteController.agregarCliente(idCliente, dni, nombreCliente, email, telefono);
			System.out.println("Cliente agregado exitosamente.");

		} catch (ClienteExceptions e) {
			System.out.println("Error al agregar cliente: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Error: Formato de número inválido.");
		}
	}

	private void listarClientes() {
		try {
			System.out.println("Lista de clientes:");
			clienteController.listarClientes().forEach(System.out::println);
		} catch (ClienteExceptions e) {
			System.out.println("Error al listar clientes: " + e.getMessage());
		}
	}

	private void eliminarCliente() {
		try {
			System.out.print("Ingrese el ID del cliente a eliminar: ");
			int idCliente = Integer.parseInt(teclado.nextLine());
			clienteController.eliminarCliente(idCliente);
			System.out.println("Cliente eliminado exitosamente.");
		} catch (ClienteExceptions e) {
			System.out.println("Error al eliminar cliente: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Error: Formato de número inválido.");
		}
	}

	private void modificarCliente() {
		try {
			System.out.print("Ingrese el ID del cliente: ");
			int idCliente = Integer.parseInt(teclado.nextLine());

			System.out.print("Ingrese el nuevo nombre del cliente: ");
			String dni = teclado.nextLine();

			System.out.print("Ingrese los nuevos apellidos del cliente: ");
			String nombreCliente = teclado.nextLine();

			System.out.print("Ingrese la nueva dirección del cliente: ");
			String email = teclado.nextLine();

			System.out.print("Ingrese el nuevo teléfono del cliente: ");
			String telefono = teclado.nextLine();

			clienteController.modificarCliente(idCliente, dni, nombreCliente, email, telefono);
			System.out.println("Cliente modificado exitosamente.");

		} catch (ClienteExceptions e) {
			System.out.println("Error al modificar cliente: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Error: Formato de número inválido.");
		}
	}
}
