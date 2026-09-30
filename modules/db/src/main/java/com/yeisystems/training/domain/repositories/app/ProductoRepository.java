package com.yeisystems.training.domain.repositories.app;

import com.yeisystems.training.domain.entities.app.ProductoEntity;
import com.yeisystems.training.domain.repositories.generic.GenericSpringDataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends GenericSpringDataRepository<ProductoEntity, Long> {
    boolean existsByCategoria_Id(Long idCategoria);
}