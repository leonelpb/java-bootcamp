package Practica4;

public class Cuenta {

	private float saldo;
	private int numeroConsignaciones;
	private int numerosRetiros;
	private float tasaAnual;
	private float comisionMensual;

	/// Setters and Getters
	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public int getNumeroConsignaciones() {
		return numeroConsignaciones;
	}

	public void setNumeroConsignaciones(int numeroConsignaciones) {
		this.numeroConsignaciones = numeroConsignaciones;
	}

	public int getNumerosRetiros() {
		return numerosRetiros;
	}

	public void setNumerosRetiros(int numerosRetiros) {
		this.numerosRetiros = numerosRetiros;
	}

	public float getTasaAnual() {
		return tasaAnual;
	}

	public void setTasaAnual(float tasaAnual) {
		this.tasaAnual = tasaAnual;
	}

	public float getComisionMensual() {
		return comisionMensual;
	}

	public void setComisionMensual(float comisionMensual) {
		this.comisionMensual = comisionMensual;
	}

	/// Constructor
	public Cuenta(float saldo, float tasaAnual) {
		this.saldo = saldo;
		this.tasaAnual = tasaAnual;
	}

	public void consignar(float cantidad) {
		saldo += cantidad;
		numeroConsignaciones++;
	}

	public boolean retirar(float cantidad) {
		if (cantidad <= saldo) {
			saldo -= cantidad;
			numerosRetiros++;
			return true;
		} else {
			return false;
		}
	}

	public void calcularInteresMensual() {
		double interesMensual = saldo * (tasaAnual / 12) / 100;
		saldo += interesMensual;
	}

	public void extractoMensual() {
		saldo -= comisionMensual;
		calcularInteresMensual();
	}
	 @Override
	public String toString() {
		return "Cuenta [saldo=" + saldo + ", numeroConsignaciones=" + numeroConsignaciones + ", numerosRetiros="
				+ numerosRetiros + ", tasaAnual=" + tasaAnual + ", comisionMensual=" + comisionMensual + "]";
	}

	public void imprimir() {
	        System.out.println("Saldo: " + saldo);
	        System.out.println("Comisión mensual: " + comisionMensual);
	        System.out.println("Número de transacciones realizadas: " + (numeroConsignaciones + numerosRetiros));
	    }
}
