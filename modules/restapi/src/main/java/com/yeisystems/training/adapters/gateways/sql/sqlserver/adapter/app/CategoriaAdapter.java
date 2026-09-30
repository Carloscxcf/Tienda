package com.yeisystems.training.adapters.gateways.sql.sqlserver.adapter.app;

import com.yeisystems.training.domain.entities.app.CategoriaEntity;
import com.yeisystems.training.domain.repositories.app.CategoriaRepository;
import com.yeisystems.training.usecases.app.ports.out.CategoriaBDGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class CategoriaAdapter implements CategoriaBDGateway {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaAdapter(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Page<CategoriaEntity> findAll(Pageable pageable) {
        return categoriaRepository.findAll(pageable); // JpaRepository ya trae este metodo listo
    }

    @Override
    public Optional<CategoriaEntity> findById(Long id) { return categoriaRepository.findById(id); }

    @Override
    public CategoriaEntity save(CategoriaEntity entity) { return categoriaRepository.save(entity); }

    @Override
    public void deleteById(Long id) { categoriaRepository.deleteById(id); }

    @Override
    public boolean existsById(Long id) { return categoriaRepository.existsById(id); }

    @Override
    public boolean existsByNombre(String nombre) { return categoriaRepository.existsByNombre(nombre); }

    @Override
    public boolean existsByNombreAndIdNot(String nombre, Long id) { return categoriaRepository.existsByNombreAndIdNot(nombre, id); }
}