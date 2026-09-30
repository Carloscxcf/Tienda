package com.yeisystems.training.usecases.app.ports.out;

import com.yeisystems.training.domain.entities.app.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface PedidoBDGateway {
    Page<PedidoEntity> findAll(Pageable pageable);
    Optional<PedidoEntity> findById(Long id);
    PedidoEntity save(PedidoEntity entity);
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByUsuarioId(Long idUsuario);
}