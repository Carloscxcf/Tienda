package com.yeisystems.training.adapters.controllers.rest.restcontroller.app;

import com.yeisystems.training.entities.app.PedidoDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/pedidos")
public interface PedidoController {

    @GetMapping
    ResponseEntity<Page<PedidoDto>> findAll(Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<PedidoDto> findById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<PedidoDto> save(@Valid @RequestBody PedidoDto dto, BindingResult result);

    @PutMapping("/{id}")
    ResponseEntity<PedidoDto> update(@PathVariable("id") Long id, @Valid @RequestBody PedidoDto dto, BindingResult result);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable("id") Long id);
}