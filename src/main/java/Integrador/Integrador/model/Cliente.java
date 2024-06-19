package Integrador.Integrador.model;

public class Cliente {
	private int idCliente;
	private String dni;
	private String nombreCliente;
	private String email;
	private String telefono;
	
	
	public Cliente(int idCliente, String dni, String nombreCliente, String email, String telefono) {
		super();
		this.idCliente = idCliente;
		this.dni = dni;
		this.nombreCliente = nombreCliente;
		this.email = email;
		this.telefono = telefono;
	}

	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", dni=" + dni + ", nombreCliente=" + nombreCliente + ", email="
				+ email + ", telefono=" + telefono + "]";
		}
	
}
