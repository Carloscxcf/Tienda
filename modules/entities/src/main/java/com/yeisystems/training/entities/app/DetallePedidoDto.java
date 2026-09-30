package com.yeisystems.training.entities.app;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class DetallePedidoDto {

    private Long id;

    @NotNull(message = "debes indicar el pedido")
    private Long idPedido;

    @NotNull(message = "debes indicar el producto")
    private Long idProducto;

    @NotNull(message = "es obligatoria")
    @Positive(message = "debe ser mayor a cero")
    private Integer cantidad;

    private BigDecimal precioUnitario; // el servidor lo fija, se ignora si el cliente lo manda

    public DetallePedidoDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdPedido() { return idPedido; }
    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }
    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
}
