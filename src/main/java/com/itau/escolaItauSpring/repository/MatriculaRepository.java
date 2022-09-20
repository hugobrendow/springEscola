package com.itau.escolaItauSpring.repository;

import com.itau.escolaItauSpring.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MatriculaRepository extends JpaRepository<Matricula, UUID> {
    List<Matricula> findAllByTurmaId(UUID idTurma);
    List<Matricula> findAllByAlunoId(UUID idAluno);
    Optional<Matricula> findByAlunoIdAndTurmaId(UUID idAluno, UUID idTurma);
}