package com.yeisystems.training.adapters.gateways.sql.sqlserver.adapter.app;

import com.yeisystems.training.domain.entities.app.ProductoEntity;
import com.yeisystems.training.domain.repositories.app.ProductoRepository;
import com.yeisystems.training.usecases.app.ports.out.ProductoBDGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ProductoAdapter implements ProductoBDGateway {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoAdapter(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Page<ProductoEntity> findAll(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    public Optional<ProductoEntity> findById(Long id) { return productoRepository.findById(id); }

    @Override
    public ProductoEntity save(ProductoEntity entity) { return productoRepository.save(entity); }

    @Override
    public void deleteById(Long id) { productoRepository.deleteById(id); }

    @Override
    public boolean existsById(Long id) { return productoRepository.existsById(id); }

    @Override
    public boolean existsByCategoriaId(Long categoriaId) {
        return productoRepository.existsByCategoria_Id(categoriaId);
    }

}