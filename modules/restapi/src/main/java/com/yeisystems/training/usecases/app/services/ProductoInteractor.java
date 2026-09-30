package com.yeisystems.training.usecases.app.services;

import com.yeisystems.training.domain.entities.app.CategoriaEntity;
import com.yeisystems.training.domain.entities.app.ProductoEntity;
import com.yeisystems.training.entities.app.ProductoDto;
import com.yeisystems.training.usecases.app.mappers.ProductoMapper;
import com.yeisystems.training.usecases.app.ports.in.ProductoUsecase;
import com.yeisystems.training.usecases.app.ports.out.CategoriaBDGateway;
import com.yeisystems.training.usecases.app.ports.out.DetallePedidoBDGateway;
import com.yeisystems.training.usecases.app.ports.out.ProductoBDGateway;
import com.yeisystems.training.usecases.exceptions.RequestConflictException;
import com.yeisystems.training.usecases.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProductoInteractor implements ProductoUsecase {

    private final ProductoBDGateway productoBDGateway;
    private final CategoriaBDGateway categoriaBDGateway;
    private final DetallePedidoBDGateway detallePedidoBDGateway;
    private final ProductoMapper productoMapper;

    public ProductoInteractor(ProductoBDGateway productoBDGateway, CategoriaBDGateway categoriaBDGateway,
                              DetallePedidoBDGateway detallePedidoBDGateway, ProductoMapper productoMapper) {
        this.productoBDGateway = productoBDGateway;
        this.categoriaBDGateway = categoriaBDGateway;
        this.detallePedidoBDGateway = detallePedidoBDGateway;
        this.productoMapper = productoMapper;
    }

    @Override
    public Page<ProductoDto> findAll(Pageable pageable) {
        return productoMapper.toDtoPage(productoBDGateway.findAll(pageable));
    }

    @Override
    public ProductoDto findById(Long id) {
        ProductoEntity entity = productoBDGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id, "PRODUCTO_NOT_FOUND"));
        return productoMapper.toDto(entity);
    }

    @Override
    public ProductoDto save(ProductoDto dto) {
        CategoriaEntity categoria = categoriaBDGateway.findById(dto.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", dto.getIdCategoria(), "CATEGORIA_NOT_FOUND"));
        ProductoEntity entity = productoMapper.toEntity(dto);
        entity.setId(null);
        entity.setCategoria(categoria);
        return productoMapper.toDto(productoBDGateway.save(entity));
    }

    @Override
    public ProductoDto update(Long id, ProductoDto dto) {
        if (!productoBDGateway.existsById(id)) {
            throw new ResourceNotFoundException("Producto", "id", id, "PRODUCTO_NOT_FOUND");
        }
        CategoriaEntity categoria = categoriaBDGateway.findById(dto.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", dto.getIdCategoria(), "CATEGORIA_NOT_FOUND"));
        ProductoEntity entity = productoMapper.toEntity(dto);
        entity.setId(id);
        entity.setCategoria(categoria);
        return productoMapper.toDto(productoBDGateway.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        if (!productoBDGateway.existsById(id)) {
            throw new ResourceNotFoundException("Producto", "id", id, "PRODUCTO_NOT_FOUND");
        }
        if (detallePedidoBDGateway.existsByProductoId(id)) {
            throw new RequestConflictException("Producto", "id", id, "PRODUCTO_EN_USO");
        }
        productoBDGateway.deleteById(id);
    }
}