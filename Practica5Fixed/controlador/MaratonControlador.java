package Practica5Fixed.controlador;

import java.util.ArrayList;

import java.util.List;

import Practica5Fixed.modelo.*;
import Practica5Fixed.modelo.excepciones.DatosInvalidosException;
import Practica5Fixed.modelo.excepciones.EquipoCompletoException;
import Practica5Fixed.modelo.validadores.ValidarProgramador;
public class MaratonControlador {

	private List<Equipo> equipos;

	public MaratonControlador() {
		this.equipos = new ArrayList<>();
	}

	public void crearEquipo(Equipo equipo) {
		equipos.add(equipo);
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void añadirProgramador(String nombreEquipo, String nombre, String apellidos)
			throws EquipoCompletoException, DatosInvalidosException {
		Equipo equipo = equipos.stream().filter(e -> e.getNombreEquipo().equalsIgnoreCase(nombreEquipo)).findFirst()
				.orElse(null);

		if (equipo != null) {
			ValidarProgramador.validarNombre(nombre);
			ValidarProgramador.validarApellidos(apellidos);
			equipo.añadirProgramador(new Programador(nombre, apellidos));
		} else {
			System.out.println("Equipo no encontrado.");
		}
	}

	public String obtenerInfoEquipo(String nombreEquipo) {
		Equipo equipo = equipos.stream().filter(e -> e.getNombreEquipo().equalsIgnoreCase(nombreEquipo)).findFirst()
				.orElse(null);

		return (equipo != null) ? equipo.toString() : "Equipo no encontrado.";
	}

	public String obtenerInfoTodosEquipos() {
		if (equipos.isEmpty()) {
			return "No hay equipos registrados.";
		}
		StringBuilder informacion = new StringBuilder();
		for (Equipo equipo : equipos) {
			informacion.append(equipo.toString()).append("\n");
		}
		return informacion.toString();
	}
}
