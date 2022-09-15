package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.mapper.OcorrenciaMapper;
import com.itau.escolaItauSpring.model.Ocorrencia;
import com.itau.escolaItauSpring.repository.OcorrenciaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OcorrenciaServiceIntegrationTest {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private OcorrenciaService ocorrenciaService;

    private static Ocorrencia ocorrencia;

    @BeforeEach
    void setUp() {
        ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao("");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void buscarOcorrencias() {
    }

    @Test
    void buscarOcorrencia() {
    }

    @Test
    void registrarOcorrencia() {
    }

    @Test
    void alterarOcorrencia() {
    }

    @Test
    void deletarOcorrencia() {
    }
}