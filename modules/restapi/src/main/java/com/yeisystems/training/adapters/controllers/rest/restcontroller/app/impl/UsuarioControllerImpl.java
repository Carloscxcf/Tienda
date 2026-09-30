package com.yeisystems.training.adapters.controllers.rest.restcontroller.app.impl;

import com.yeisystems.training.adapters.controllers.rest.restcontroller.app.UsuarioController;
import com.yeisystems.training.adapters.controllers.rest.restcontroller.generic.GenericRestController;
import com.yeisystems.training.entities.app.UsuarioDto;
import com.yeisystems.training.usecases.app.ports.in.UsuarioUsecase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioControllerImpl extends GenericRestController implements UsuarioController {

    private final UsuarioUsecase usuarioUsecase;

    public UsuarioControllerImpl(UsuarioUsecase usuarioUsecase) {
        this.usuarioUsecase = usuarioUsecase;
    }

    @Override
    public ResponseEntity<Page<UsuarioDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(usuarioUsecase.findAll(pageable));
    }

    @Override
    public ResponseEntity<UsuarioDto> findById(Long id) {
        return ResponseEntity.ok(usuarioUsecase.findById(id));
    }

    @Override
    public ResponseEntity<UsuarioDto> save(UsuarioDto dto, BindingResult result) {
        UsuarioDto validado = validate(dto, result);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioUsecase.save(validado));
    }

    @Override
    public ResponseEntity<UsuarioDto> update(Long id, UsuarioDto dto, BindingResult result) {
        UsuarioDto validado = validate(dto, result);
        return ResponseEntity.ok(usuarioUsecase.update(id, validado));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        usuarioUsecase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}