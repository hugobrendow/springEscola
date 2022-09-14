package com.itau.escolaItauSpring.repository;

import com.itau.escolaItauSpring.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MatriculaRepository extends JpaRepository<Matricula, UUID> {
}
