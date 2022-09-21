package com.itau.escolaItauSpring.repository;

import com.itau.escolaItauSpring.model.UsuarioPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, UUID> {
    Optional<UsuarioPerfil> findByUsuarioIdAndPerfilId(UUID usuarioId, UUID perfilId);
}
