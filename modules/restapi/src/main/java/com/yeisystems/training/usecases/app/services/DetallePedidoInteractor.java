package com.yeisystems.training.usecases.app.services;

import com.yeisystems.training.domain.entities.app.DetallePedidoEntity;
import com.yeisystems.training.domain.entities.app.PedidoEntity;
import com.yeisystems.training.domain.entities.app.ProductoEntity;
import com.yeisystems.training.entities.app.DetallePedidoDto;
import com.yeisystems.training.usecases.app.mappers.DetallePedidoMapper;
import com.yeisystems.training.usecases.app.ports.in.DetallePedidoUsecase;
import com.yeisystems.training.usecases.app.ports.out.DetallePedidoBDGateway;
import com.yeisystems.training.usecases.app.ports.out.PedidoBDGateway;
import com.yeisystems.training.usecases.app.ports.out.ProductoBDGateway;
import com.yeisystems.training.usecases.exceptions.RequestConflictException;
import com.yeisystems.training.usecases.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class DetallePedidoInteractor implements DetallePedidoUsecase {

    private final DetallePedidoBDGateway detallePedidoBDGateway;
    private final PedidoBDGateway pedidoBDGateway;
    private final ProductoBDGateway productoBDGateway;
    private final DetallePedidoMapper detallePedidoMapper;

    public DetallePedidoInteractor(DetallePedidoBDGateway detallePedidoBDGateway, PedidoBDGateway pedidoBDGateway,
                                   ProductoBDGateway productoBDGateway, DetallePedidoMapper detallePedidoMapper) {
        this.detallePedidoBDGateway = detallePedidoBDGateway;
        this.pedidoBDGateway = pedidoBDGateway;
        this.productoBDGateway = productoBDGateway;
        this.detallePedidoMapper = detallePedidoMapper;
    }

    @Override
    public Page<DetallePedidoDto> findAll(Pageable pageable) {
        return detallePedidoMapper.toDtoPage(detallePedidoBDGateway.findAll(pageable));
    }

    @Override
    public DetallePedidoDto findById(Long id) {
        DetallePedidoEntity entity = detallePedidoBDGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetallePedido", "id", id, "DETALLE_NOT_FOUND"));
        return detallePedidoMapper.toDto(entity);
    }

    @Override
    public DetallePedidoDto save(DetallePedidoDto dto) {
        PedidoEntity pedido = pedidoBDGateway.findById(dto.getIdPedido())
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", "id", dto.getIdPedido(), "PEDIDO_NOT_FOUND"));
        ProductoEntity producto = productoBDGateway.findById(dto.getIdProducto())
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", dto.getIdProducto(), "PRODUCTO_NOT_FOUND"));

        if (producto.getStock() < dto.getCantidad()) {
            throw new RequestConflictException("Producto", "stock", producto.getStock(), "STOCK_INSUFICIENTE");
        }
        producto.setStock(producto.getStock() - dto.getCantidad());
        productoBDGateway.save(producto);

        DetallePedidoEntity entity = detallePedidoMapper.toEntity(dto);
        entity.setId(null);
        entity.setPedido(pedido);
        entity.setProducto(producto);
        entity.setPrecioUnitario(producto.getPrecio()); // se congela el precio actual, el cliente no lo decide

        return detallePedidoMapper.toDto(detallePedidoBDGateway.save(entity));
    }

    @Override
    public DetallePedidoDto update(Long id, DetallePedidoDto dto) {
        DetallePedidoEntity existente = detallePedidoBDGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetallePedido", "id", id, "DETALLE_NOT_FOUND"));

        // Solo se permite cambiar la Cantidad. El producto y el pedido no se reasignan.
        int cantidadAnterior = existente.getCantidad();
        int cantidadNueva = dto.getCantidad();
        int delta = cantidadNueva - cantidadAnterior;

        ProductoEntity producto = existente.getProducto();
        if (delta > 0) {
            if (producto.getStock() < delta) {
                throw new RequestConflictException("Producto", "stock", producto.getStock(), "STOCK_INSUFICIENTE");
            }
            producto.setStock(producto.getStock() - delta);
        } else if (delta < 0) {
            producto.setStock(producto.getStock() + Math.abs(delta));
        }
        productoBDGateway.save(producto);

        existente.setCantidad(cantidadNueva);
        return detallePedidoMapper.toDto(detallePedidoBDGateway.save(existente));
    }

    @Override
    public void deleteById(Long id) {
        DetallePedidoEntity existente = detallePedidoBDGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetallePedido", "id", id, "DETALLE_NOT_FOUND"));

        ProductoEntity producto = existente.getProducto();
        producto.setStock(producto.getStock() + existente.getCantidad()); // se regresa el stock
        productoBDGateway.save(producto);

        detallePedidoBDGateway.deleteById(id);
    }
}