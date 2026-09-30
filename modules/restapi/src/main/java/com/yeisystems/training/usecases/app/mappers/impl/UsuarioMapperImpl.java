package com.yeisystems.training.usecases.app.mappers.impl;

import com.yeisystems.training.domain.entities.app.UsuarioEntity;
import com.yeisystems.training.entities.app.UsuarioDto;
import com.yeisystems.training.usecases.app.mappers.UsuarioMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDto toDto(UsuarioEntity entity) {
        if (entity == null) return null;
        return new UsuarioDto(entity.getId(), entity.getNombres(), entity.getApellidoPaterno(), entity.getApellidoMaterno());
    }

    @Override
    public UsuarioEntity toEntity(UsuarioDto dto) {
        if (dto == null) return null;
        return new UsuarioEntity(dto.getId(), dto.getNombres(), dto.getApellidoPaterno(), dto.getApellidoMaterno());
    }

    @Override
    public List<UsuarioDto> toDtoList(List<UsuarioEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<UsuarioDto> toDtoPage(Page<UsuarioEntity> page) {
        return page.map(this::toDto);
    }
}