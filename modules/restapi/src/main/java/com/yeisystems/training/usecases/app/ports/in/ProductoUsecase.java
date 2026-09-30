package com.yeisystems.training.usecases.app.ports.in;

import com.yeisystems.training.entities.app.ProductoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoUsecase {
    Page<ProductoDto> findAll(Pageable pageable);
    ProductoDto findById(Long id);
    ProductoDto save(ProductoDto dto);
    ProductoDto update(Long id, ProductoDto dto);
    void deleteById(Long id);
}