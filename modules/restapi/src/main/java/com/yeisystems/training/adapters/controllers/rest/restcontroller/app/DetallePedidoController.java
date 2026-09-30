package com.yeisystems.training.adapters.controllers.rest.restcontroller.app;

import com.yeisystems.training.entities.app.DetallePedidoDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/detallepedidos")
public interface DetallePedidoController {

    @GetMapping
    ResponseEntity<Page<DetallePedidoDto>> findAll(Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<DetallePedidoDto> findById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<DetallePedidoDto> save(@Valid @RequestBody DetallePedidoDto dto, BindingResult result);

    @PutMapping("/{id}")
    ResponseEntity<DetallePedidoDto> update(@PathVariable("id") Long id, @Valid @RequestBody DetallePedidoDto dto, BindingResult result);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable("id") Long id);
}