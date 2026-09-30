package com.yeisystems.training.adapters.gateways.sql.sqlserver.adapter.app;

import com.yeisystems.training.domain.entities.app.DetallePedidoEntity;
import com.yeisystems.training.domain.repositories.app.DetallePedidoRepository;
import com.yeisystems.training.usecases.app.ports.out.DetallePedidoBDGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class DetallePedidoAdapter implements DetallePedidoBDGateway {

    private final DetallePedidoRepository detallePedidoRepository;

    @Autowired
    public DetallePedidoAdapter(DetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    @Override
    public Page<DetallePedidoEntity> findAll(Pageable pageable) {
        return detallePedidoRepository.findAll(pageable);
    }

    @Override
    public Optional<DetallePedidoEntity> findById(Long id) { return detallePedidoRepository.findById(id); }

    @Override
    public DetallePedidoEntity save(DetallePedidoEntity entity) { return detallePedidoRepository.save(entity); }

    @Override
    public void deleteById(Long id) { detallePedidoRepository.deleteById(id); }

    @Override
    public boolean existsById(Long id) { return detallePedidoRepository.existsById(id); }

    @Override
    public boolean existsByProductoId(Long idProducto) { return detallePedidoRepository.existsByProducto_Id(idProducto); }
}