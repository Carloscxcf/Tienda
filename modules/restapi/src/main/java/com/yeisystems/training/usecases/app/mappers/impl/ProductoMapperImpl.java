package com.yeisystems.training.usecases.app.mappers.impl;

import com.yeisystems.training.domain.entities.app.ProductoEntity;
import com.yeisystems.training.entities.app.ProductoDto;
import com.yeisystems.training.usecases.app.mappers.ProductoMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

public class ProductoMapperImpl implements ProductoMapper {

    @Override
    public ProductoDto toDto(ProductoEntity entity) {
        if (entity == null) return null;
        Long idCategoria = entity.getCategoria() != null ? entity.getCategoria().getId() : null;
        return new ProductoDto(entity.getId(), entity.getNombre(), entity.getPrecio(),
                entity.getDescripcion(), entity.getStock(), idCategoria);
    }

    @Override
    public ProductoEntity toEntity(ProductoDto dto) {
        if (dto == null) return null;
        ProductoEntity entity = new ProductoEntity();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setPrecio(dto.getPrecio());
        entity.setDescripcion(dto.getDescripcion());
        entity.setStock(dto.getStock());
        // OJO: aqui NO se pone la Categoria. Eso lo hace el Interactor.
        return entity;
    }

    @Override
    public List<ProductoDto> toDtoList(List<ProductoEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<ProductoDto> toDtoPage(Page<ProductoEntity> page) {
        return page.map(this::toDto);
    }
}