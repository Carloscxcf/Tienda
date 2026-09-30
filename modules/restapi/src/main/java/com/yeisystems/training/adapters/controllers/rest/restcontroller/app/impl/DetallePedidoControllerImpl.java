package com.yeisystems.training.adapters.controllers.rest.restcontroller.app.impl;

import com.yeisystems.training.adapters.controllers.rest.restcontroller.app.DetallePedidoController;
import com.yeisystems.training.adapters.controllers.rest.restcontroller.generic.GenericRestController;
import com.yeisystems.training.entities.app.DetallePedidoDto;
import com.yeisystems.training.usecases.app.ports.in.DetallePedidoUsecase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetallePedidoControllerImpl extends GenericRestController implements DetallePedidoController {

    private final DetallePedidoUsecase detallePedidoUsecase;

    public DetallePedidoControllerImpl(DetallePedidoUsecase detallePedidoUsecase) {
        this.detallePedidoUsecase = detallePedidoUsecase;
    }

    @Override
    public ResponseEntity<Page<DetallePedidoDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(detallePedidoUsecase.findAll(pageable));
    }

    @Override
    public ResponseEntity<DetallePedidoDto> findById(Long id) {
        return ResponseEntity.ok(detallePedidoUsecase.findById(id));
    }

    @Override
    public ResponseEntity<DetallePedidoDto> save(DetallePedidoDto dto, BindingResult result) {
        DetallePedidoDto validado = validate(dto, result);
        return ResponseEntity.status(HttpStatus.CREATED).body(detallePedidoUsecase.save(validado));
    }

    @Override
    public ResponseEntity<DetallePedidoDto> update(Long id, DetallePedidoDto dto, BindingResult result) {
        DetallePedidoDto validado = validate(dto, result);
        return ResponseEntity.ok(detallePedidoUsecase.update(id, validado));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        detallePedidoUsecase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}