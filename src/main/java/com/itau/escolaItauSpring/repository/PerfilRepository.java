package com.itau.escolaItauSpring.repository;

import com.itau.escolaItauSpring.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, UUID> {
    List<Perfil> findByUsuarioPerfis_UsuarioId(UUID id);
}
