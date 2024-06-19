package Integrador.Integrador;

import java.util.Scanner;

import Integrador.Integrador.controller.ClienteController;
import Integrador.Integrador.controller.FacturaController;
import Integrador.Integrador.controller.ProductoController;
import Integrador.Integrador.model.exceptions.ProductExceptions;
import Integrador.Integrador.view.Vista;
import Integrador.Integrador.view.VistaClientes;
import Integrador.Integrador.view.VistaFacturas;

public class App {
	public static void main(String[] args) throws ProductExceptions {
		ProductoController productoController = new ProductoController();
		ClienteController clienteController = new ClienteController();
		FacturaController facturaController = new FacturaController();

		try (Scanner teclado = new Scanner(System.in)) {
			Vista vistaProductos = new Vista(productoController, teclado);
			VistaClientes vistaClientes = new VistaClientes(clienteController, teclado);
			VistaFacturas vistaFacturas = new VistaFacturas(facturaController, teclado);

			while (true) {
				System.out.println("Seleccione una opción:");
				System.out.println("1. Gestionar productos");
				System.out.println("2. Gestionar clientes");
				System.out.println("3. Gestionar facturas");
				System.out.println("4. Salir");

				int opcion = Integer.parseInt(teclado.nextLine());

				switch (opcion) {
				case 1:
					vistaProductos.mostrarMenu();
					break;
				case 2:
					vistaClientes.mostrarMenuClientes();
					break;
				case 3:
					vistaFacturas.mostrarMenuFacturas();
					break;
				case 4:
					System.out.println("Saliendo...");
					return;
				default:
					System.out.println("Opción inválida, intente nuevamente.");
				}
			}
		}
	}
}
