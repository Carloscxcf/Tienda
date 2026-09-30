package com.yeisystems.training.usecases.app.ports.in;

import com.yeisystems.training.entities.app.CategoriaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaUsecase {
    Page<CategoriaDto> findAll(Pageable pageable);
    CategoriaDto findById(Long id);
    CategoriaDto save(CategoriaDto dto);
    CategoriaDto update(Long id, CategoriaDto dto);
    void deleteById(Long id);
}