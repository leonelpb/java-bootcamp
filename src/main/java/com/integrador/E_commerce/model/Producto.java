package com.integrador.E_commerce.model;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*; // Para las anotaciones de JPA
import lombok.Data; // Para las anotaciones de Lombok

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "productos") // Nombre de la tabla en la base de datos
@Data // Lombok genera automáticamente getters, setters, toString, etc.
public class Producto {

    @Id
    @JsonProperty("id")
    private Long productId;

    @Column(nullable = false)
    @JsonProperty("title")// Campo obligatorio en la tabla
    private String nombre;

    @JsonProperty("description")
    @Column(length = 255)
    @Size(max = 255)
    private String descripcion;
    
    @JsonProperty("image")
    private String imagen;

    @Column(nullable = false)
    @JsonProperty("price")
    private Double precio;

    private Integer stock;

    // Constructor, getters, setters y toString generados automáticamente por Lombok.
    @ManyToOne // Muchos productos pueden pertenecer a una categoría
    @JoinColumn(name = "categoria_id") // Define la columna que será la foreign key
    private Categoria categoria;
    
    
	@OneToMany(mappedBy = "producto", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	private List<CartItem> products = new ArrayList<>();

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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
}