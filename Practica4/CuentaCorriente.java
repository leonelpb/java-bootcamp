package Practica4;

public class CuentaCorriente extends Cuenta {

	public CuentaCorriente(float saldo, float tasaAnual) {
		super(saldo, tasaAnual);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void consignar(float cantidad) {
		// TODO Auto-generated method stub
		super.consignar(cantidad);
	}

	@Override
	public boolean retirar(float cantidad) {
		// TODO Auto-generated method stub
		return super.retirar(cantidad);
	}

	@Override
	public void extractoMensual() {
		// TODO Auto-generated method stub
		super.extractoMensual();
	}

}
