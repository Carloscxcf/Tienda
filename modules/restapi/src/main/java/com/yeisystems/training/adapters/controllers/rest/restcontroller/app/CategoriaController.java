package com.yeisystems.training.adapters.controllers.rest.restcontroller.app;

import com.yeisystems.training.entities.app.CategoriaDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/categorias")
public interface CategoriaController {

    @GetMapping
    ResponseEntity<Page<CategoriaDto>> findAll(Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<CategoriaDto> findById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<CategoriaDto> save(@Valid @RequestBody CategoriaDto dto, BindingResult result);

    @PutMapping("/{id}")
    ResponseEntity<CategoriaDto> update(@PathVariable("id") Long id, @Valid @RequestBody CategoriaDto dto, BindingResult result);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable("id") Long id);
}