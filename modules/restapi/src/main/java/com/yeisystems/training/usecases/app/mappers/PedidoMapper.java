package com.yeisystems.training.usecases.app.mappers;

import com.yeisystems.training.domain.entities.app.PedidoEntity;
import com.yeisystems.training.entities.app.PedidoDto;
import org.springframework.data.domain.Page;
import java.util.List;

public interface PedidoMapper {
    PedidoDto toDto(PedidoEntity entity);
    PedidoEntity toEntity(PedidoDto dto);
    List<PedidoDto> toDtoList(List<PedidoEntity> entities);
    Page<PedidoDto> toDtoPage(Page<PedidoEntity> page);
}