package Practica5Fixed;

import Practica5Fixed.controlador.MaratonControlador;
import Practica5Fixed.vista.MaratonVista;

public class App 
{
    public static void main( String[] args )
    {
        MaratonControlador controlador = new MaratonControlador();
        MaratonVista vista = new MaratonVista(controlador);
        vista.Inicio();
    }
}
