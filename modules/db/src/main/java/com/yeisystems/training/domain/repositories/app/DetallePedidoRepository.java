package com.yeisystems.training.domain.repositories.app;

import com.yeisystems.training.domain.entities.app.DetallePedidoEntity;
import com.yeisystems.training.domain.repositories.generic.GenericSpringDataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends GenericSpringDataRepository<DetallePedidoEntity, Long> {
    boolean existsByProducto_Id(Long idProducto);
}