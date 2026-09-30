package com.yeisystems.training.adapters.controllers.rest.restcontroller.app;

import com.yeisystems.training.entities.app.UsuarioDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/usuarios")
public interface UsuarioController {

    @GetMapping
    ResponseEntity<Page<UsuarioDto>> findAll(Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<UsuarioDto> findById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<UsuarioDto> save(@Valid @RequestBody UsuarioDto dto, BindingResult result);

    @PutMapping("/{id}")
    ResponseEntity<UsuarioDto> update(@PathVariable("id") Long id, @Valid @RequestBody UsuarioDto dto, BindingResult result);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable("id") Long id);
}