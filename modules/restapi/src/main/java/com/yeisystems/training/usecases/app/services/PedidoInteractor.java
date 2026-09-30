package com.yeisystems.training.usecases.app.services;

import com.yeisystems.training.domain.entities.app.PedidoEntity;
import com.yeisystems.training.domain.entities.app.UsuarioEntity;
import com.yeisystems.training.entities.app.PedidoDto;
import com.yeisystems.training.usecases.app.mappers.PedidoMapper;
import com.yeisystems.training.usecases.app.ports.in.PedidoUsecase;
import com.yeisystems.training.usecases.app.ports.out.PedidoBDGateway;
import com.yeisystems.training.usecases.app.ports.out.UsuarioBDGateway;
import com.yeisystems.training.usecases.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PedidoInteractor implements PedidoUsecase {

    private final PedidoBDGateway pedidoBDGateway;
    private final UsuarioBDGateway usuarioBDGateway;
    private final PedidoMapper pedidoMapper;

    public PedidoInteractor(PedidoBDGateway pedidoBDGateway, UsuarioBDGateway usuarioBDGateway, PedidoMapper pedidoMapper) {
        this.pedidoBDGateway = pedidoBDGateway;
        this.usuarioBDGateway = usuarioBDGateway;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public Page<PedidoDto> findAll(Pageable pageable) {
        return pedidoMapper.toDtoPage(pedidoBDGateway.findAll(pageable));
    }

    @Override
    public PedidoDto findById(Long id) {
        PedidoEntity entity = pedidoBDGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", "id", id, "PEDIDO_NOT_FOUND"));
        return pedidoMapper.toDto(entity);
    }

    @Override
    public PedidoDto save(PedidoDto dto) {
        UsuarioEntity usuario = usuarioBDGateway.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", dto.getIdUsuario(), "USUARIO_NOT_FOUND"));
        PedidoEntity entity = pedidoMapper.toEntity(dto);
        entity.setId(null);
        entity.setUsuario(usuario);
        entity.setFecha(LocalDate.now());   // el servidor decide, no el cliente
        entity.setHora(LocalTime.now());    // el servidor decide, no el cliente
        entity.setEstado("PENDIENTE");      // todo pedido nuevo arranca igual
        return pedidoMapper.toDto(pedidoBDGateway.save(entity));
    }

    @Override
    public PedidoDto update(Long id, PedidoDto dto) {
        PedidoEntity existente = pedidoBDGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", "id", id, "PEDIDO_NOT_FOUND"));
        // Solo se permite cambiar el Estado (ej. marcarlo como ENVIADO o CANCELADO).
        // Fecha, Hora y Usuario NO se tocan una vez creado el pedido.
        if (dto.getEstado() != null && !dto.getEstado().isBlank()) {
            existente.setEstado(dto.getEstado());
        }
        return pedidoMapper.toDto(pedidoBDGateway.save(existente));
    }

    @Override
    public void deleteById(Long id) {
        if (!pedidoBDGateway.existsById(id)) {
            throw new ResourceNotFoundException("Pedido", "id", id, "PEDIDO_NOT_FOUND");
        }
        pedidoBDGateway.deleteById(id);
    }
}