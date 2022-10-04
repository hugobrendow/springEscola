package com.itau.escolaItauSpring.repository;

import com.itau.escolaItauSpring.model.CursoDisciplina;
import com.itau.escolaItauSpring.model.Professor;
import com.itau.escolaItauSpring.model.ProfessorCursoDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfessorCursoDisciplinaRepository extends JpaRepository<ProfessorCursoDisciplina, UUID> {

    public Optional<ProfessorCursoDisciplina> findOneByProfessorAndCursoDisciplina(Professor professor, CursoDisciplina cursoDisciplina);

}
