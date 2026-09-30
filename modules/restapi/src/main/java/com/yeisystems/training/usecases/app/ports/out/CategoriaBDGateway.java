package com.yeisystems.training.usecases.app.ports.out;

import com.yeisystems.training.domain.entities.app.CategoriaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface CategoriaBDGateway {
    Page<CategoriaEntity> findAll(Pageable pageable);
    Optional<CategoriaEntity> findById(Long id);
    CategoriaEntity save(CategoriaEntity entity);
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByNombre(String nombre);
    boolean existsByNombreAndIdNot(String nombre, Long id);
}