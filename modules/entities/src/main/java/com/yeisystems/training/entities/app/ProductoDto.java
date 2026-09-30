package com.yeisystems.training.entities.app;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class ProductoDto {

    private Long id;

    @NotBlank(message = "es obligatorio")
    private String nombre;

    @NotNull(message = "es obligatorio")
    @Positive(message = "debe ser mayor a cero")
    private BigDecimal precio;

    private String descripcion;

    @NotNull(message = "es obligatorio")
    @PositiveOrZero(message = "no puede ser negativo")
    private Integer stock;

    @NotNull(message = "debes indicar la categoria")
    private Long idCategoria;

    public ProductoDto() {}

    public ProductoDto(Long id, String nombre, BigDecimal precio, String descripcion, Integer stock, Long idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.idCategoria = idCategoria;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public Long getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Long idCategoria) { this.idCategoria = idCategoria; }
}