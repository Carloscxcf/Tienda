package com.yeisystems.training.usecases.app.ports.out;

import com.yeisystems.training.domain.entities.app.ProductoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface ProductoBDGateway {
    Page<ProductoEntity> findAll(Pageable pageable);
    Optional<ProductoEntity> findById(Long id);
    ProductoEntity save(ProductoEntity entity);
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByCategoriaId(Long categoriaId);
}