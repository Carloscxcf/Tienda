package com.yeisystems.training.domain.repositories.app;

import com.yeisystems.training.domain.entities.app.UsuarioEntity;
import com.yeisystems.training.domain.repositories.generic.GenericSpringDataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends GenericSpringDataRepository<UsuarioEntity, Long> {
}