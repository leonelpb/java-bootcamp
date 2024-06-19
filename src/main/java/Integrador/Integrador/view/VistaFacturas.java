package Integrador.Integrador.view;

import java.sql.Date;
import java.util.Scanner;

import Integrador.Integrador.controller.FacturaController;
import Integrador.Integrador.model.exceptions.FacturaExceptions;

public class VistaFacturas {
	private FacturaController facturaController;
	private Scanner teclado;

	public VistaFacturas(FacturaController facturaController, Scanner teclado) {
		this.facturaController = facturaController;
		this.teclado = teclado;
	}

	public void mostrarMenuFacturas() {
		while (true) {
			System.out.println("Seleccione una opción:");
			System.out.println("1. Agregar factura");
			System.out.println("2. Listar facturas");
			System.out.println("3. Listar facturas por cliente");
			System.out.println("4. Volver al menú principal");

			int opcion = Integer.parseInt(teclado.nextLine());

			switch (opcion) {
			case 1:
				agregarFactura();
				break;
			case 2:
				listarFacturas();
				break;
			case 3:
				listarFacturasPorCliente();
				break;
			case 4:
				return;
			default:
				System.out.println("Opción inválida, intente nuevamente.");
			}
		}
	}

	private void agregarFactura() {
		try {
			System.out.print("Ingrese el ID de la factura: ");
			int numeroFactura = Integer.parseInt(teclado.nextLine());

			System.out.print("Ingrese el ID del cliente: ");
			int idCliente = Integer.parseInt(teclado.nextLine());
			
			System.out.print("Ingrese el ID del producto a facturar: ");
			int idProducto = Integer.parseInt(teclado.nextLine());

			System.out.print("Ingrese la fecha (yyyy-mm-dd): ");
			Date fecha = java.sql.Date.valueOf(teclado.nextLine());

			System.out.print("Ingrese el total de la factura: ");
			double total = Double.parseDouble(teclado.nextLine());

			facturaController.agregarFactura(numeroFactura, idCliente, idProducto, fecha, total);
			System.out.println("Factura agregada exitosamente.");

		} catch (FacturaExceptions e) {
			System.out.println("Error al agregar factura: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Error: Formato de número inválido.");
		}
	}

	private void listarFacturas() {
		try {
			System.out.println("Lista de facturas:");
			facturaController.listarFacturas().forEach(System.out::println);
		} catch (FacturaExceptions e) {
			System.out.println("Error al listar facturas: " + e.getMessage());
		}
	}

	private void listarFacturasPorCliente() {
		try {
			System.out.print("Ingrese el ID del cliente: ");
			int idCliente = Integer.parseInt(teclado.nextLine());
			System.out.println("Facturas del cliente:");
			facturaController.listarFacturasPorCliente(idCliente).forEach(System.out::println);
		} catch (FacturaExceptions e) {
			System.out.println("Error al listar facturas del cliente: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Error: Formato de número inválido.");
		}
	}
}
