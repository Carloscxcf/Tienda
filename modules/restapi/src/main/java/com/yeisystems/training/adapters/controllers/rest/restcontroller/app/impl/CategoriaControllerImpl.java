package com.yeisystems.training.adapters.controllers.rest.restcontroller.app.impl;

import com.yeisystems.training.adapters.controllers.rest.restcontroller.app.CategoriaController;
import com.yeisystems.training.adapters.controllers.rest.restcontroller.generic.GenericRestController;
import com.yeisystems.training.entities.app.CategoriaDto;
import com.yeisystems.training.usecases.app.ports.in.CategoriaUsecase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaControllerImpl extends GenericRestController implements CategoriaController {

    private final CategoriaUsecase categoriaUsecase;

    public CategoriaControllerImpl(CategoriaUsecase categoriaUsecase) {
        this.categoriaUsecase = categoriaUsecase;
    }

    @Override
    public ResponseEntity<Page<CategoriaDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(categoriaUsecase.findAll(pageable));
    }

    @Override
    public ResponseEntity<CategoriaDto> findById(Long id) {
        return ResponseEntity.ok(categoriaUsecase.findById(id));
    }

    @Override
    public ResponseEntity<CategoriaDto> save(CategoriaDto dto, BindingResult result) {
        CategoriaDto validado = validate(dto, result);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaUsecase.save(validado));
    }

    @Override
    public ResponseEntity<CategoriaDto> update(Long id, CategoriaDto dto, BindingResult result) {
        CategoriaDto validado = validate(dto, result);
        return ResponseEntity.ok(categoriaUsecase.update(id, validado));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        categoriaUsecase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}