package com.yeisystems.training.usecases.app.services;

import com.yeisystems.training.domain.entities.app.CategoriaEntity;
import com.yeisystems.training.entities.app.CategoriaDto;
import com.yeisystems.training.usecases.app.mappers.CategoriaMapper;
import com.yeisystems.training.usecases.app.ports.in.CategoriaUsecase;
import com.yeisystems.training.usecases.app.ports.out.CategoriaBDGateway;
import com.yeisystems.training.usecases.app.ports.out.ProductoBDGateway;
import com.yeisystems.training.usecases.exceptions.RequestConflictException;
import com.yeisystems.training.usecases.exceptions.ResourceNotFoundException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CategoriaInteractor implements CategoriaUsecase {

    private final CategoriaBDGateway categoriaBDGateway;
    private final ProductoBDGateway productoBDGateway;
    private final CategoriaMapper categoriaMapper;

    public CategoriaInteractor(CategoriaBDGateway categoriaBDGateway, ProductoBDGateway productoBDGateway, CategoriaMapper categoriaMapper) {
        this.categoriaBDGateway = categoriaBDGateway;
        this.productoBDGateway = productoBDGateway;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public Page<CategoriaDto> findAll(Pageable pageable) {
        return categoriaMapper.toDtoPage(categoriaBDGateway.findAll(pageable));
    }

    @Override
    public CategoriaDto findById(Long id) {
        CategoriaEntity entity = categoriaBDGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id, "CATEGORIA_NOT_FOUND"));
        return categoriaMapper.toDto(entity);
    }

    @Override
    public CategoriaDto save(CategoriaDto dto) {
        if (categoriaBDGateway.existsByNombre(dto.getNombre())) {
            throw new RequestConflictException("Categoria", "nombre", dto.getNombre(), "CATEGORIA_DUPLICADA");
        }
        CategoriaEntity entity = categoriaMapper.toEntity(dto);
        entity.setId(null);
        return categoriaMapper.toDto(categoriaBDGateway.save(entity));
    }

    @Override
    public CategoriaDto update(Long id, CategoriaDto dto) {
        if (!categoriaBDGateway.existsById(id)) {
            throw new ResourceNotFoundException("Categoria", "id", id, "CATEGORIA_NOT_FOUND");
        }
        if (categoriaBDGateway.existsByNombreAndIdNot(dto.getNombre(), id)) {
            throw new RequestConflictException("Categoria", "nombre", dto.getNombre(), "CATEGORIA_DUPLICADA");
        }
        CategoriaEntity entity = categoriaMapper.toEntity(dto);
        entity.setId(id);
        return categoriaMapper.toDto(categoriaBDGateway.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        if (!categoriaBDGateway.existsById(id)) {
            throw new ResourceNotFoundException("Categoria", "id", id, "CATEGORIA_NOT_FOUND");
        }
        if (productoBDGateway.existsByCategoriaId(id)) {
            throw new RequestConflictException("Categoria", "id", id, "CATEGORIA_EN_USO");
        }
        categoriaBDGateway.deleteById(id);
    }
}