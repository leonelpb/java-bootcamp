package Practica5Fixed.vista;

import java.util.List;
import java.util.Scanner;

import Practica5Fixed.controlador.MaratonControlador;
import Practica5Fixed.modelo.Equipo;
import Practica5Fixed.modelo.enumeraciones.Universidades;
import Practica5Fixed.modelo.excepciones.DatosInvalidosException;
import Practica5Fixed.modelo.excepciones.EquipoCompletoException;


public class MaratonVista {
	private MaratonControlador controlador;
	private Scanner scanner;

	public MaratonVista(MaratonControlador controlador) {
		this.controlador = controlador;
		this.scanner = new Scanner(System.in);
	}

	public void Inicio() {
		while (true) {
			System.out.println("1. Crear equipo");
			System.out.println("2. Añadir miembro a equipo existente");
			System.out.println("3. Ver listado de equipos");
			System.out.println("4. Salir");
			System.out.print("Seleccione una opción: ");
			int opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
			case 1:
				crearEquipo();
				break;
			case 2:
				añadirProgramador();
				break;
			case 3:
				verListaEquipo();
				break;
			case 4:
				System.out.println("Saliendo...");
				return;
			default:
				System.out.println("Opción no válida");
				break;
			}
		}
	}

	public void crearEquipo() {
		System.out.print("Ingrese el nombre del equipo: ");
		String nombreEquipo = scanner.nextLine();

		Universidades universidad = seleccionarUniversidad();

		System.out.print("Ingrese el lenguaje de programación que va a utilizar el equipo: ");
		String lenguajeProgramacion = scanner.nextLine();

		int tamañoEquipo = seleccionarTamañoEquipo();

		Equipo equipo = new Equipo(nombreEquipo, universidad, lenguajeProgramacion, tamañoEquipo) {
		};
		controlador.crearEquipo(equipo);
		System.out.println("Equipo creado exitosamente.");
	}

	public Universidades seleccionarUniversidad() {
		System.out.println("Seleccione una universidad:");
		for (Universidades universidad : Universidades.values()) {
			System.out.println(universidad.ordinal() + 1 + ". " + universidad.getNombre());
		}
		int opcion = scanner.nextInt();
		scanner.nextLine();

		if (opcion > 0 && opcion <= Universidades.values().length) {
			return Universidades.values()[opcion - 1];
		} else {
			System.out.println("Opción no válida. Seleccionando por defecto 'Universidad Blas Pascal'.");
			return Universidades.UNIVERSIDAD_BLAS_PASCAL;
		}

	}

	public int seleccionarTamañoEquipo() {
		System.out.print("Ingrese el tamaño del equipo (mínimo " + Equipo.MAX_PARTICIPANTES + ", máximo "
				+ Equipo.MIN_PARTICIPANTES + "): ");
		int tamañoEquipo = scanner.nextInt();
		scanner.nextLine();

		if (tamañoEquipo >= Equipo.MAX_PARTICIPANTES && tamañoEquipo <= Equipo.MIN_PARTICIPANTES) {
			return tamañoEquipo;
		} else {
			System.out.println("Tamaño no válido. Seleccionando por defecto " + Equipo.MAX_PARTICIPANTES + ".");
			return Equipo.MAX_PARTICIPANTES;
		}
	}

	public void añadirProgramador() {
		List<Equipo> equipos = controlador.getEquipos();
		if (equipos.isEmpty()) {
			System.out.println("Felicidades, son el primer team en inscribirse.");
			return;
		}

		System.out.println("Equipos existentes:");
		for (Equipo equipo : equipos) {
			System.out.println("- " + equipo.getNombreEquipo());
		}

		System.out.print("Ingrese el nombre del equipo al que desea añadir un miembro: ");
		String nombreEquipo = scanner.nextLine();

		System.out.print("Ingrese nombre del programador: ");
		String nombre = scanner.nextLine();
		System.out.print("Ingrese apellidos del programador: ");
		String apellidos = scanner.nextLine();

		try {
			controlador.añadirProgramador(nombreEquipo, nombre, apellidos);
			System.out.println("Miembro añadido exitosamente.");
		} catch (EquipoCompletoException | DatosInvalidosException e) {
			System.out.println(e.getMessage());
		}
	}

	public void verListaEquipo() {
		String informacion = controlador.obtenerInfoTodosEquipos();
		System.out.println(informacion);
	}
}
