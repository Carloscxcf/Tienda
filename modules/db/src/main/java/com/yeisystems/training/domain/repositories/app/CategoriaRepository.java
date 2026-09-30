package com.yeisystems.training.domain.repositories.app;

import com.yeisystems.training.domain.entities.app.CategoriaEntity;
import com.yeisystems.training.domain.repositories.generic.GenericSpringDataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends GenericSpringDataRepository<CategoriaEntity, Long> {
    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdNot(String nombre, Long id);
}