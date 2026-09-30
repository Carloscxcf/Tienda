package com.yeisystems.training.entities.app;

import jakarta.validation.constraints.NotBlank;

public class UsuarioDto {

    private Long id;

    @NotBlank(message = "es obligatorio")
    private String nombres;

    @NotBlank(message = "es obligatorio")
    private String apellidoPaterno;

    private String apellidoMaterno;

    public UsuarioDto() {}

    public UsuarioDto(Long id, String nombres, String apellidoPaterno, String apellidoMaterno) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }
    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }
}