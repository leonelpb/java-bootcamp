package Practica4;

public class Principal {

	public static void main(String[] args) {
		
		CuentaCorriente  CC1 = new CuentaCorriente(200,300);
		CuentaCorriente  CC2 = new CuentaCorriente(500,300);
		CuentaAhorros CA1 = new CuentaAhorros(150,200);
		CuentaAhorros CA2 = new CuentaAhorros(1000,150);
		

		System.out.println(CC1.toString());
		CC1.consignar(5200);
		System.out.println("Estado de la cuenta luego de consignar"  + CC1.toString());
		
		System.out.println(CC2.toString());
		CC2.calcularInteresMensual();
		System.out.println("Estado de la cuenta luego de Calcular interes mensual"  + CC2.toString());
		
		System.out.println(CA1.toString());
		CA1.retirar(50);
		System.out.println("Estado de la cuenta luego de retirar"  + CA1.toString());
		
		System.out.println(CA2.toString());
		CA2.consignar(5200);
		CA2.extractoMensual();
		CA2.setComisionMensual(20);
		System.out.println("Estado de la cuenta luego de consignar y realizar extracto mensual"
				+ ""  + CA2.toString() );
		
		


	}

}
