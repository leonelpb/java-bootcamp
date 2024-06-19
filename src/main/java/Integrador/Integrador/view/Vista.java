package Integrador.Integrador.view;

import java.sql.SQLException;
import java.util.Scanner;

import Integrador.Integrador.controller.ProductoController;
import Integrador.Integrador.model.exceptions.ProductExceptions;

public class Vista {
	private final ProductoController productoController;
	private final Scanner teclado;

	public Vista(ProductoController productoController, Scanner teclado) {
		this.productoController = productoController;
		this.teclado = teclado;
	}

	public void mostrarMenu() throws ProductExceptions {
		int opcion;
		do {
			System.out.println("Menu Principal:");
			System.out.println("1. Agregar Producto");
			System.out.println("2. Modificar Stock");
			System.out.println("3. Listar Productos");
			System.out.println("4. Salir");
			System.out.print("Elija una opción: ");
			opcion = teclado.nextInt();
			teclado.nextLine();

			switch (opcion) {
			case 1:
				agregarProducto();
				break;
			case 2:
				modificarStock();
				break;
			case 3:
				listarProductos();
				break;
			case 4:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida.");
			}
		} while (opcion != 4);
	}

	private void agregarProducto() {
		try {
			System.out.print("Ingrese ID del producto: ");
			int idProducto = teclado.nextInt();
			teclado.nextLine(); 
			System.out.print("Ingrese nombre del producto: ");
			String nombreProducto = teclado.nextLine();
			System.out.print("Ingrese precio del producto: ");
			double precio = Double.parseDouble(teclado.nextLine());
			System.out.print("Ingrese stock del producto: ");
			int stock =Integer.parseInt(teclado.nextLine());
			System.out.print("Ingrese descripcion del producto: ");
			String descripcion = teclado.nextLine();
			
			productoController.agregarProducto(idProducto, nombreProducto, stock, precio, descripcion);
			System.out.println("Producto añadido exitosamente.");
		} catch (SQLException | ProductExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	private void modificarStock() {
		try {
			System.out.print("Ingrese ID del producto: ");
			int idProducto = teclado.nextInt();
			System.out.print("Ingrese la cantidad a añadir al stock: ");
			int cantidad = teclado.nextInt();
			productoController.modificarStock(idProducto, cantidad);
			System.out.println("Stock modificado exitosamente.");
		} catch (SQLException | ProductExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	private void listarProductos() throws ProductExceptions{
		try {
			productoController.listarProductos().forEach(System.out::println);
		} catch (SQLException e) {
			System.out.println("Error al listar los productos: " + e.getMessage());
		}
	}
}
