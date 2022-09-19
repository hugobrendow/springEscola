package com.itau.escolaItauSpring.repository;

import com.itau.escolaItauSpring.model.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, UUID> {
    List<Ocorrencia> findByAluno_Id(UUID id);
}
