package Practica5Fixed.modelo;

public class Programador {
	private String nombre;
	private String apellidos;
	
	

	public Programador(String nombre, String apellidos) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellidos;
	}
	public void setApellido(String apellido) {
		this.apellidos = apellido;
	}
	
	@Override
	public String toString() {
		return "Programador [nombre=" + nombre + ", apellido=" + apellidos + "]";
	}
}
