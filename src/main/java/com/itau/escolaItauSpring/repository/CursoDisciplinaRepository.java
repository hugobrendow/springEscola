package com.itau.escolaItauSpring.repository;

import com.itau.escolaItauSpring.model.CursoDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CursoDisciplinaRepository extends JpaRepository<CursoDisciplina, UUID> {
}
