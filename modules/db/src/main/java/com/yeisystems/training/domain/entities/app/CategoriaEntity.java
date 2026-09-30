package com.yeisystems.training.domain.entities.app;

import jakarta.persistence.*;

@Entity
@Table(name = "Categoria")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCategoria")
    private Long id;

    @Column(name = "Nombre", nullable = false, length = 100, unique = true)
    private String nombre;

    public CategoriaEntity() {}

    public CategoriaEntity(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
