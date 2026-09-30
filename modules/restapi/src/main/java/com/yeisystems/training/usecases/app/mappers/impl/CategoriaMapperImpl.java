package com.yeisystems.training.usecases.app.mappers.impl;

import com.yeisystems.training.domain.entities.app.CategoriaEntity;
import com.yeisystems.training.entities.app.CategoriaDto;
import com.yeisystems.training.usecases.app.mappers.CategoriaMapper;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriaMapperImpl implements CategoriaMapper {

    @Override
    public CategoriaDto toDto(CategoriaEntity entity) {
        if (entity == null) return null;
        return new CategoriaDto(entity.getId(), entity.getNombre());
    }

    @Override
    public CategoriaEntity toEntity(CategoriaDto dto) {
        if (dto == null) return null;
        return new CategoriaEntity(dto.getId(), dto.getNombre());
    }

    @Override
    public List<CategoriaDto> toDtoList(List<CategoriaEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<CategoriaDto> toDtoPage(Page<CategoriaEntity> page) {
        return page.map(this::toDto); // Page.map() convierte el contenido y conserva la info de paginado
    }
}