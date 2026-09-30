package com.yeisystems.training.domain.entities.app;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Producto")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDProducto")
    private Long id;

    @Column(name = "Nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "Precio", nullable = false)
    private BigDecimal precio;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Stock", nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "IDCategoria", nullable = false)
    private CategoriaEntity categoria;

    public ProductoEntity() {}

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
    public CategoriaEntity getCategoria() { return categoria; }
    public void setCategoria(CategoriaEntity categoria) { this.categoria = categoria; }
}
