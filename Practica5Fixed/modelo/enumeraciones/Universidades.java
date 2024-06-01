package Practica5Fixed.modelo.enumeraciones;

public enum Universidades {
    UNIVERSIDAD_BLAS_PASCAL("Universidad Blas Pascal"),
    UNC("UNC"),
    UPC("UPC"),
    UTN("UTN"),
    SIGLO_21("Siglo 21");

    private final String nombre;

    Universidades(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}