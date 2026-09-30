package com.yeisystems.training.adapters.gateways.sql.sqlserver.adapter.app;

import com.yeisystems.training.domain.entities.app.UsuarioEntity;
import com.yeisystems.training.domain.repositories.app.UsuarioRepository;
import com.yeisystems.training.usecases.app.ports.out.UsuarioBDGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class UsuarioAdapter implements UsuarioBDGateway {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioAdapter(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Page<UsuarioEntity> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public Optional<UsuarioEntity> findById(Long id) { return usuarioRepository.findById(id); }

    @Override
    public UsuarioEntity save(UsuarioEntity entity) { return usuarioRepository.save(entity); }

    @Override
    public void deleteById(Long id) { usuarioRepository.deleteById(id); }

    @Override
    public boolean existsById(Long id) { return usuarioRepository.existsById(id); }
}