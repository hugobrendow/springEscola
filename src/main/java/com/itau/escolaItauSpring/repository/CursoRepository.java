package com.itau.escolaItauSpring.repository;

import com.itau.escolaItauSpring.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CursoRepository extends JpaRepository<Curso, UUID> {
    @Query("select c from Curso c where c.nome like %:nome%")
    Page<Curso> findByNome(String nome, Pageable pageable);
}
