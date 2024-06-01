package Practica5Fixed.modelo;

import java.util.ArrayList;
import java.util.List;

import Practica5Fixed.modelo.enumeraciones.Universidades;
import Practica5Fixed.modelo.excepciones.DatosInvalidosException;
import Practica5Fixed.modelo.excepciones.EquipoCompletoException;
import Practica5Fixed.modelo.interfaces.euipoConstantes;
import Practica5Fixed.modelo.validadores.ValidarProgramador;

public abstract class Equipo implements euipoConstantes {
	private String nombreEquipo;
	private Universidades universidad;
	private String lenguaje;
	private int nIntegrantes;
	private List<Programador> programadores;

	public Equipo(String nombreEquipo, Universidades universidad, String lenguaje, int nIntegrantes) {
		super();
		this.nombreEquipo = nombreEquipo;
		this.universidad = universidad;
		this.lenguaje = lenguaje;
		this.nIntegrantes = nIntegrantes;
		this.programadores = new ArrayList<>();
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEqupo) {
		this.nombreEquipo = nombreEqupo;
	}

	public Universidades getUniversidad() {
		return universidad;
	}

	public void setUniversidad(Universidades universidad) {
		this.universidad = universidad;
	}

	public String getLenguaje() {
		return lenguaje;
	}

	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}

	public int getnIntegrantes() {
		return nIntegrantes;
	}

	public void setnIntegrantes(int nIntegrantes) {
		this.nIntegrantes = nIntegrantes;
	}

	public List<Programador> getProgramadores() {
		return programadores;
	}

	public void setProgramadores(List<Programador> programadores) {
		this.programadores = programadores;
	}

	public boolean equipoCompleto() {
		return programadores.size() >= MIN_PARTICIPANTES;
	}

	public void añadirProgramador(Programador programador) throws EquipoCompletoException, DatosInvalidosException {
		if (equipoCompleto()) {
			throw new EquipoCompletoException("El equipo ya esta completo.");
		}
		ValidarProgramador.validarNombre(programador.getNombre());
		ValidarProgramador.validarApellidos(programador.getApellido());
		programadores.add(programador);
	}

	@Override
	public String toString() {
		return "Equipo [nombreEquipo=" + nombreEquipo + ", universidad=" + universidad + ", lenguaje=" + lenguaje
				+ ", nIntegrantes=" + nIntegrantes + ", programadores=" + programadores + "]";
	}

}
