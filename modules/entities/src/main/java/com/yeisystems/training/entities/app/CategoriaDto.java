package com.yeisystems.training.entities.app;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaDto {

    private Long id;

    @NotBlank(message = "es obligatorio")
    @Size(max = 100, message = "no debe exceder 100 caracteres")
    private String nombre;

    public CategoriaDto() {}

    public CategoriaDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}