package com.integrador.E_commerce.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "categorias")
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    // Relación de uno a muchos (Una categoría tiene muchos productos)
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos; 
}
