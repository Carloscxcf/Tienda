package com.yeisystems.training.usecases.app.mappers;

import com.yeisystems.training.domain.entities.app.CategoriaEntity;
import com.yeisystems.training.entities.app.CategoriaDto;
import org.springframework.data.domain.Page;
import java.util.List;

public interface CategoriaMapper {
    CategoriaDto toDto(CategoriaEntity entity);
    CategoriaEntity toEntity(CategoriaDto dto);
    List<CategoriaDto> toDtoList(List<CategoriaEntity> entities);
    Page<CategoriaDto> toDtoPage(Page<CategoriaEntity> page);
}