package com.yeisystems.training.usecases.app.mappers;

import com.yeisystems.training.domain.entities.app.ProductoEntity;
import com.yeisystems.training.entities.app.ProductoDto;
import org.springframework.data.domain.Page;
import java.util.List;

public interface ProductoMapper {
    ProductoDto toDto(ProductoEntity entity);
    ProductoEntity toEntity(ProductoDto dto);
    List<ProductoDto> toDtoList(List<ProductoEntity> entities);
    Page<ProductoDto> toDtoPage(Page<ProductoEntity> page);
}