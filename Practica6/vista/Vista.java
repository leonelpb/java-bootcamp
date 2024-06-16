package Practica6.Practica6.vista;

import java.util.Scanner;

import Practica6.Practica6.DAO.ClienteDAO;
import Practica6.Practica6.DAO.FacturaDAO;
import Practica6.Practica6.DAO.ProductoDAO;
import Practica6.Practica6.modelo.Cliente;
import Practica6.Practica6.modelo.Factura;
import Practica6.Practica6.modelo.Producto;

public class Vista {

	private static final Scanner teclado = new Scanner(System.in);
	private static final ProductoDAO productoDao = new ProductoDAO();
	private static final ClienteDAO clienteDao = new ClienteDAO();
	private static final FacturaDAO facturaDao = new FacturaDAO();

	public static void main(String[] args) {
		int option;
		do {
			showMenu();
			option = teclado.nextInt();
			teclado.nextLine();

			switch (option) {
			case 1:
				agregarProducto();
				break;
			case 2:
				listarProductos();
				break;
			case 3:
				agregarCliente();
				break;
			case 4:
				listarClientes();
				break;
			case 5:
				agregarFactura();
				break;
			case 6:
				listarFacturas();
				break;
			case 7:
				System.out.println("Saliendo del sistema...");
				break;
			default:
				System.out.println("Opción inválida, intente nuevamente.");

			}
		} while (option != 9);

	}

	private static void listarFacturas() {
		System.out.println("Lista de facturas:");
		for (Factura factura : facturaDao.listar()) {
			System.out.println(factura);
		}
	}

	private static void agregarFactura() {
		System.out.print("Ingrese el numero de la factura: ");
		int numeroFactura = teclado.nextInt();
		System.out.print("Ingrese el numero de la factura: ");
		double total = teclado.nextInt();
		System.out.print("Ingrese el ID del Cliente: ");
		int idCliente = teclado.nextInt();
		System.out.print("Ingrese el ID del producto facturado: ");
		int idProducto = teclado.nextInt();
		Factura factura = new Factura(numeroFactura, total, idCliente, idProducto);
		facturaDao.crear(factura);
		System.out.print("Cliente creado con exito");

	}

	private static void listarClientes() {
		System.out.println("Lista de clientes:");
		for (Cliente cliente : clienteDao.listar()) {
			System.out.println(cliente);
		}
	}

	private static void agregarCliente() {
		System.out.print("Ingrese el DNI del cliente: ");
		String dni = teclado.nextLine();
		System.out.print("Ingrese el nombre del cliente: ");
		String nombreCliente = teclado.nextLine();
		System.out.print("Ingrese el email del cliente: ");
		String email = teclado.nextLine();
		Cliente cliente = new Cliente(dni, nombreCliente, email);
		clienteDao.crear(cliente);
		System.out.print("Cliente creado con exito");

	}

	private static void listarProductos() {
		System.out.println("Lista de productos :");
		for (Producto producto : productoDao.listar()) {
			System.out.println(producto);
		}

	}

	private static void agregarProducto() {
		System.out.print("Ingrese el nombre del producto: ");
		String nombreProducto = teclado.nextLine();
		System.out.print("Ingrese el precio del producto: ");
		double precio = teclado.nextDouble();

		Producto producto = new Producto(0, nombreProducto, 0, precio);
		productoDao.crear(producto);
		System.out.println("Producto agregado exitosamente.");
	}

	private static void showMenu() {
		System.out.println("Menú de Opciones");
		System.out.println("1. Agregar Producto");
		System.out.println("2. Listar Productos");
		System.out.println("3. Agregar Cliente");
		System.out.println("4. Listar Clientes");
		System.out.println("5. Agregar Factura");
		System.out.println("6. Listar Facturas");
		System.out.println("7. Salir");
		System.out.print("Seleccione una opción: ");
	}

}
