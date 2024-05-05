/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practica3java;
import java.util.Scanner;

/**
 *
 * @author Leonel
 */
public class Practica3Java {
    
    private static final int PIN = 1234;
    private static final double SALDO_INICIAL = 5000000;
    private static double saldo = SALDO_INICIAL;
    
    
    public static void main(String[] args) {
      
        Scanner teclaOpt = new Scanner(System.in);
        int pinIngresado;
        
        do{
            System.out.println("Por favor ingrese su PIN de 4 digitos");
            pinIngresado = teclaOpt.nextInt();
            if (pinIngresado != PIN) {
                System.out.println("PIN incorrecto, intente de nuevo");
            }
        }while(pinIngresado != PIN);
        
        
        int opt;
        
        do{
            System.out.println("\nBienvenido al Cajero Automático");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Realizar depósito");
            System.out.println("3. Realizar retiro");
            System.out.println("4. Salir");
            System.out.print("Por favor, seleccione una opción: ");
            opt = teclaOpt.nextInt();
            
            switch(opt){
                case 1 :
                    consultarSaldo();
                    break;
                case 2:
                    System.out.print("Ingrese la cantidad a depositar: ");
                    double cantidadDeposito = teclaOpt.nextDouble();
                    depositar(cantidadDeposito);
                    break;
                case 3:
                    System.out.print("Ingrese la cantidad a retirar: ");
                    double cantidadRetiro = teclaOpt.nextDouble();
                    retirar(cantidadRetiro);
                    break;
                case 4:
                    System.out.println("Gracias por utilizar el Cajero Automático. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");                    
            }
        }while( opt != 4);
          
    teclaOpt.close();
    
    }
        
    private static void consultarSaldo() {
        System.out.println("Su saldo actual es: $" + saldo);
    }
    
    private static void depositar(double cantidad) {
        if (cantidad <= 0) {
            System.out.println("La cantidad a depositar debe ser mayor que cero.");
        } else {
            saldo += cantidad;
            System.out.println("Depósito realizado correctamente. Su nuevo saldo es: $" + saldo);
        }
    }
    
    private static void retirar(double cantidad) {
        if (cantidad <= 0) {
            System.out.println("La cantidad a retirar debe ser mayor que cero.");
        } else if (cantidad > saldo) {
            System.out.println("Saldo insuficiente.");
        } else {
            saldo -= cantidad;
            System.out.println("Retiro realizado correctamente. Su nuevo saldo es: $" + saldo);
        }
    }
}
