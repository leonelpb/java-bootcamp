package com.integrador.E_commerce.model;
import jakarta.persistence.*; // Para las anotaciones de JPA
import lombok.Data; // Para las anotaciones de Lombok

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "productos") // Nombre de la tabla en la base de datos
@Data // Lombok genera automáticamente getters, setters, toString, etc.
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autogenera el ID
    private Long id;

    @Column(nullable = false) // Campo obligatorio en la tabla
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    private Integer stock;

    // Constructor, getters, setters y toString generados automáticamente por Lombok.
    @ManyToOne // Muchos productos pueden pertenecer a una categoría
    @JoinColumn(name = "categoria_id") // Define la columna que será la foreign key
    private Categoria categoria;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	
}