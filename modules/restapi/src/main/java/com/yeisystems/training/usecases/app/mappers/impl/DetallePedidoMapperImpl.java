package com.yeisystems.training.usecases.app.mappers.impl;

import com.yeisystems.training.domain.entities.app.DetallePedidoEntity;
import com.yeisystems.training.entities.app.DetallePedidoDto;
import com.yeisystems.training.usecases.app.mappers.DetallePedidoMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class DetallePedidoMapperImpl implements DetallePedidoMapper {

    @Override
    public DetallePedidoDto toDto(DetallePedidoEntity entity) {
        if (entity == null) return null;
        DetallePedidoDto dto = new DetallePedidoDto();
        dto.setId(entity.getId());
        dto.setIdPedido(entity.getPedido() != null ? entity.getPedido().getId() : null);
        dto.setIdProducto(entity.getProducto() != null ? entity.getProducto().getId() : null);
        dto.setCantidad(entity.getCantidad());
        dto.setPrecioUnitario(entity.getPrecioUnitario());
        return dto;
    }

    @Override
    public DetallePedidoEntity toEntity(DetallePedidoDto dto) {
        if (dto == null) return null;
        DetallePedidoEntity entity = new DetallePedidoEntity();
        entity.setId(dto.getId());
        entity.setCantidad(dto.getCantidad());
        // pedido, producto y precioUnitario los fija el Interactor
        return entity;
    }

    @Override
    public List<DetallePedidoDto> toDtoList(List<DetallePedidoEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<DetallePedidoDto> toDtoPage(Page<DetallePedidoEntity> page) {
        return page.map(this::toDto);
    }
}