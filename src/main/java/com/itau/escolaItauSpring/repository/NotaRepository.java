package com.itau.escolaItauSpring.repository;

import com.itau.escolaItauSpring.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotaRepository extends JpaRepository<Nota, UUID> {
}
