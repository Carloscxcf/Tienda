package com.yeisystems.training.usecases.app.ports.out;

import com.yeisystems.training.domain.entities.app.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface UsuarioBDGateway {
    Page<UsuarioEntity> findAll(Pageable pageable);
    Optional<UsuarioEntity> findById(Long id);
    UsuarioEntity save(UsuarioEntity entity);
    void deleteById(Long id);
    boolean existsById(Long id);
}