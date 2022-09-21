package com.itau.escolaItauSpring.repository;

import com.itau.escolaItauSpring.model.ProfessorCursoDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfessorCursoDisciplinaRepository extends JpaRepository<ProfessorCursoDisciplina, UUID> {

}
