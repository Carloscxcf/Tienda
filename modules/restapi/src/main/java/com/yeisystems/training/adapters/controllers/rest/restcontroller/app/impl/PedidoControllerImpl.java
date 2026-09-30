package com.yeisystems.training.adapters.controllers.rest.restcontroller.app.impl;

import com.yeisystems.training.adapters.controllers.rest.restcontroller.app.PedidoController;
import com.yeisystems.training.adapters.controllers.rest.restcontroller.generic.GenericRestController;
import com.yeisystems.training.entities.app.PedidoDto;
import com.yeisystems.training.usecases.app.ports.in.PedidoUsecase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoControllerImpl extends GenericRestController implements PedidoController {

    private final PedidoUsecase pedidoUsecase;

    public PedidoControllerImpl(PedidoUsecase pedidoUsecase) {
        this.pedidoUsecase = pedidoUsecase;
    }

    @Override
    public ResponseEntity<Page<PedidoDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(pedidoUsecase.findAll(pageable));
    }

    @Override
    public ResponseEntity<PedidoDto> findById(Long id) {
        return ResponseEntity.ok(pedidoUsecase.findById(id));
    }

    @Override
    public ResponseEntity<PedidoDto> save(PedidoDto dto, BindingResult result) {
        PedidoDto validado = validate(dto, result);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoUsecase.save(validado));
    }

    @Override
    public ResponseEntity<PedidoDto> update(Long id, PedidoDto dto, BindingResult result) {
        PedidoDto validado = validate(dto, result);
        return ResponseEntity.ok(pedidoUsecase.update(id, validado));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        pedidoUsecase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}