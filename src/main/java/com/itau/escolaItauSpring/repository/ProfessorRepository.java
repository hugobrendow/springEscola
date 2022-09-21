package com.itau.escolaItauSpring.repository;

import com.itau.escolaItauSpring.enums.NivelProfessorEnum;
import com.itau.escolaItauSpring.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, UUID> {


       Page<Professor> findProfessorByNomeContainingIgnoreCaseOrCpfContainingOrNivel(String nome, String cpf, NivelProfessorEnum nivel, Pageable pageable);

}