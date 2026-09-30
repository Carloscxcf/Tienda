package com.yeisystems.training.usecases.app.mappers;

import com.yeisystems.training.domain.entities.app.UsuarioEntity;
import com.yeisystems.training.entities.app.UsuarioDto;
import org.springframework.data.domain.Page;
import java.util.List;

public interface UsuarioMapper {
    UsuarioDto toDto(UsuarioEntity entity);
    UsuarioEntity toEntity(UsuarioDto dto);
    List<UsuarioDto> toDtoList(List<UsuarioEntity> entities);
    Page<UsuarioDto> toDtoPage(Page<UsuarioEntity> page);
}