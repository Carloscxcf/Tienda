package com.yeisystems.training.domain.entities.app;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUsuario")
    private Long id;

    @Column(name = "Nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "ApellidoPaterno", nullable = false, length = 100)
    private String apellidoPaterno;

    @Column(name = "ApellidoMaterno", length = 100)
    private String apellidoMaterno;

    public UsuarioEntity() {}

    public UsuarioEntity(Long id, String nombres, String apellidoPaterno, String apellidoMaterno) {
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