package com.yeisystems.training.adapters.controllers.rest.restcontroller.app;

import com.yeisystems.training.entities.app.ProductoDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/productos")
public interface ProductoController {

    @GetMapping
    ResponseEntity<Page<ProductoDto>> findAll(Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<ProductoDto> findById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<ProductoDto> save(@Valid @RequestBody ProductoDto dto, BindingResult result);

    @PutMapping("/{id}")
    ResponseEntity<ProductoDto> update(@PathVariable("id") Long id, @Valid @RequestBody ProductoDto dto, BindingResult result);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable("id") Long id);
}