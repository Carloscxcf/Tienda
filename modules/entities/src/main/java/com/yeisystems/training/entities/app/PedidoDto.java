package com.yeisystems.training.entities.app;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class PedidoDto {

    private Long id;

    @NotNull(message = "debes indicar el usuario")
    private Long idUsuario;

    private LocalDate fecha;   // el servidor la fija al crear, se ignora si el cliente la manda
    private LocalTime hora;    // idem
    private String estado;    // el servidor la fija al crear; en update si se puede cambiar

    public PedidoDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}