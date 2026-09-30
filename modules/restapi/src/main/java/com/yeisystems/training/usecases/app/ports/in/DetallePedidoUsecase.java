package com.yeisystems.training.usecases.app.ports.in;

import com.yeisystems.training.entities.app.DetallePedidoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DetallePedidoUsecase {
    Page<DetallePedidoDto> findAll(Pageable pageable);
    DetallePedidoDto findById(Long id);
    DetallePedidoDto save(DetallePedidoDto dto);
    DetallePedidoDto update(Long id, DetallePedidoDto dto);
    void deleteById(Long id);
}