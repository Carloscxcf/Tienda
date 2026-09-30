package com.yeisystems.training.adapters.gateways.sql.sqlserver.adapter.app;

import com.yeisystems.training.domain.entities.app.PedidoEntity;
import com.yeisystems.training.domain.repositories.app.PedidoRepository;
import com.yeisystems.training.usecases.app.ports.out.PedidoBDGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class PedidoAdapter implements PedidoBDGateway {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoAdapter(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Page<PedidoEntity> findAll(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    @Override
    public Optional<PedidoEntity> findById(Long id) { return pedidoRepository.findById(id); }

    @Override
    public PedidoEntity save(PedidoEntity entity) { return pedidoRepository.save(entity); }

    @Override
    public void deleteById(Long id) { pedidoRepository.deleteById(id); }

    @Override
    public boolean existsById(Long id) { return pedidoRepository.existsById(id); }

    @Override
    public boolean existsByUsuarioId(Long idUsuario) { return pedidoRepository.existsByUsuario_Id(idUsuario); }
}