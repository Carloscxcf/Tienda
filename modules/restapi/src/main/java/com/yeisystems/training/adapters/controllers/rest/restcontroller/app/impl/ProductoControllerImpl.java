package com.yeisystems.training.adapters.controllers.rest.restcontroller.app.impl;

import com.yeisystems.training.adapters.controllers.rest.restcontroller.app.ProductoController;
import com.yeisystems.training.adapters.controllers.rest.restcontroller.generic.GenericRestController;
import com.yeisystems.training.entities.app.ProductoDto;
import com.yeisystems.training.usecases.app.ports.in.ProductoUsecase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoControllerImpl extends GenericRestController implements ProductoController {

    private final ProductoUsecase productoUsecase;

    public ProductoControllerImpl(ProductoUsecase productoUsecase) {
        this.productoUsecase = productoUsecase;
    }

    @Override
    public ResponseEntity<Page<ProductoDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(productoUsecase.findAll(pageable));
    }

    @Override
    public ResponseEntity<ProductoDto> findById(Long id) {
        return ResponseEntity.ok(productoUsecase.findById(id));
    }

    @Override
    public ResponseEntity<ProductoDto> save(ProductoDto dto, BindingResult result) {
        ProductoDto validado = validate(dto, result);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoUsecase.save(validado));
    }

    @Override
    public ResponseEntity<ProductoDto> update(Long id, ProductoDto dto, BindingResult result) {
        ProductoDto validado = validate(dto, result);
        return ResponseEntity.ok(productoUsecase.update(id, validado));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        productoUsecase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}