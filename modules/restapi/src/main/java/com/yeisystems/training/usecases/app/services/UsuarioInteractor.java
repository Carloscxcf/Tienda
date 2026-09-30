package com.yeisystems.training.usecases.app.services;

import com.yeisystems.training.domain.entities.app.UsuarioEntity;
import com.yeisystems.training.entities.app.UsuarioDto;
import com.yeisystems.training.usecases.app.mappers.UsuarioMapper;
import com.yeisystems.training.usecases.app.ports.in.UsuarioUsecase;
import com.yeisystems.training.usecases.app.ports.out.PedidoBDGateway;
import com.yeisystems.training.usecases.app.ports.out.UsuarioBDGateway;
import com.yeisystems.training.usecases.exceptions.RequestConflictException;
import com.yeisystems.training.usecases.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class UsuarioInteractor implements UsuarioUsecase {

    private final UsuarioBDGateway usuarioBDGateway;
    private final PedidoBDGateway pedidoBDGateway;
    private final UsuarioMapper usuarioMapper;

    public UsuarioInteractor(UsuarioBDGateway usuarioBDGateway, PedidoBDGateway pedidoBDGateway, UsuarioMapper usuarioMapper) {
        this.usuarioBDGateway = usuarioBDGateway;
        this.pedidoBDGateway = pedidoBDGateway;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Page<UsuarioDto> findAll(Pageable pageable) {
        return usuarioMapper.toDtoPage(usuarioBDGateway.findAll(pageable));
    }

    @Override
    public UsuarioDto findById(Long id) {
        UsuarioEntity entity = usuarioBDGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id, "USUARIO_NOT_FOUND"));
        return usuarioMapper.toDto(entity);
    }

    @Override
    public UsuarioDto save(UsuarioDto dto) {
        UsuarioEntity entity = usuarioMapper.toEntity(dto);
        entity.setId(null);
        return usuarioMapper.toDto(usuarioBDGateway.save(entity));
    }

    @Override
    public UsuarioDto update(Long id, UsuarioDto dto) {
        if (!usuarioBDGateway.existsById(id)) {
            throw new ResourceNotFoundException("Usuario", "id", id, "USUARIO_NOT_FOUND");
        }
        UsuarioEntity entity = usuarioMapper.toEntity(dto);
        entity.setId(id);
        return usuarioMapper.toDto(usuarioBDGateway.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        if (!usuarioBDGateway.existsById(id)) {
            throw new ResourceNotFoundException("Usuario", "id", id, "USUARIO_NOT_FOUND");
        }
        if (pedidoBDGateway.existsByUsuarioId(id)) {
            throw new RequestConflictException("Usuario", "id", id, "USUARIO_EN_USO");
        }
        usuarioBDGateway.deleteById(id);
    }
}