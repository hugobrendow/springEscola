package com.itau.escolaItauSpring.repository;

import com.itau.escolaItauSpring.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotaRepository extends JpaRepository<Nota, UUID> {
    List<Nota> findByMatriculaId(UUID id);
    List<Nota> findByCursoDisciplinaId(UUID id);
    Optional<Nota> findByCursoDisciplinaIdAndMatriculaId(UUID cursoDisciplinaId, UUID matriculaId);
}
