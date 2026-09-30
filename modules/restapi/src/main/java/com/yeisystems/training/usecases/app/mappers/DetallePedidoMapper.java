package com.yeisystems.training.usecases.app.mappers;

import com.yeisystems.training.domain.entities.app.DetallePedidoEntity;
import com.yeisystems.training.entities.app.DetallePedidoDto;
import org.springframework.data.domain.Page;
import java.util.List;

public interface DetallePedidoMapper {
    DetallePedidoDto toDto(DetallePedidoEntity entity);
    DetallePedidoEntity toEntity(DetallePedidoDto dto);
    List<DetallePedidoDto> toDtoList(List<DetallePedidoEntity> entities);
    Page<DetallePedidoDto> toDtoPage(Page<DetallePedidoEntity> page);
}