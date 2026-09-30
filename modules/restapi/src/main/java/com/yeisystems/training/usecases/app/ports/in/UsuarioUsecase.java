package com.yeisystems.training.usecases.app.ports.in;

import com.yeisystems.training.entities.app.UsuarioDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioUsecase {
    Page<UsuarioDto> findAll(Pageable pageable);
    UsuarioDto findById(Long id);
    UsuarioDto save(UsuarioDto dto);
    UsuarioDto update(Long id, UsuarioDto dto);
    void deleteById(Long id);
}