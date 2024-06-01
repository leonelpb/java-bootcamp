package Practica5Fixed.modelo.validadores;

import Practica5Fixed.modelo.excepciones.DatosInvalidosException;

public class ValidarProgramador {
	public static void validarNombre(String nombre) throws DatosInvalidosException{
		if(!nombre.matches("[a-zA-Z]+")) {
			throw new DatosInvalidosException("El nombre solo debe incluir letras");
		}
	}
	
	public static void validarApellidos(String apellidos) throws DatosInvalidosException{
		if(apellidos.matches("[a-zA-Z\\\\s]+")) {
			throw new DatosInvalidosException ("Los apellidos solo deben incluir letras");
		}
	}
}
