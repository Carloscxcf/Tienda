package com.yeisystems.training.usecases.app.ports.in;

import com.yeisystems.training.entities.app.PedidoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoUsecase {
    Page<PedidoDto> findAll(Pageable pageable);
    PedidoDto findById(Long id);
    PedidoDto save(PedidoDto dto);
    PedidoDto update(Long id, PedidoDto dto);
    void deleteById(Long id);
}