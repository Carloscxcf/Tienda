package com.yeisystems.training.domain.repositories.app;

import com.yeisystems.training.domain.entities.app.PedidoEntity;
import com.yeisystems.training.domain.repositories.generic.GenericSpringDataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends GenericSpringDataRepository<PedidoEntity, Long> {
    boolean existsByUsuario_Id(Long idUsuario);
}
