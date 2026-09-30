package com.yeisystems.training.usecases.app.ports.out;

import com.yeisystems.training.domain.entities.app.DetallePedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface DetallePedidoBDGateway {
    Page<DetallePedidoEntity> findAll(Pageable pageable);
    Optional<DetallePedidoEntity> findById(Long id);
    DetallePedidoEntity save(DetallePedidoEntity entity);
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByProductoId(Long idProducto);
}