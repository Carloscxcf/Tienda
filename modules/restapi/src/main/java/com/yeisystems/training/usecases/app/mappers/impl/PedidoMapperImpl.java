package com.yeisystems.training.usecases.app.mappers.impl;

import com.yeisystems.training.domain.entities.app.PedidoEntity;
import com.yeisystems.training.entities.app.PedidoDto;
import com.yeisystems.training.usecases.app.mappers.PedidoMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapperImpl implements PedidoMapper {

    @Override
    public PedidoDto toDto(PedidoEntity entity) {
        if (entity == null) return null;
        PedidoDto dto = new PedidoDto();
        dto.setId(entity.getId());
        dto.setIdUsuario(entity.getUsuario() != null ? entity.getUsuario().getId() : null);
        dto.setFecha(entity.getFecha());
        dto.setHora(entity.getHora());
        dto.setEstado(entity.getEstado());
        return dto;
    }

    @Override
    public PedidoEntity toEntity(PedidoDto dto) {
        if (dto == null) return null;
        PedidoEntity entity = new PedidoEntity();
        entity.setId(dto.getId());
        // usuario, fecha, hora y estado los fija el Interactor, no el mapper
        return entity;
    }

    @Override
    public List<PedidoDto> toDtoList(List<PedidoEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<PedidoDto> toDtoPage(Page<PedidoEntity> page) {
        return page.map(this::toDto);
    }
}