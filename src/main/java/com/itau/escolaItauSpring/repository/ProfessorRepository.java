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

       @Query("select p from Professor p where 1 = 1 and " +
               "( p.nome is null or p.nome like '%:nome%' ) and " +
               "( p.cpf is null or p.cpf like '%:cpf%' ) and " +
               "( p.nivel is null or p.nivel = :nivel )")
       Page<Professor> findProfessorByParam(String nome, String cpf, NivelProfessorEnum nivel, Pageable pageable);

}