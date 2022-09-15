package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.OcorrenciaRequest;
import com.itau.escolaItauSpring.dto.response.OcorrenciaResponse;
import com.itau.escolaItauSpring.model.Ocorrencia;
import com.itau.escolaItauSpring.repository.OcorrenciaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class OcorrenciaServiceIntegrationTest {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private OcorrenciaService ocorrenciaService;

    private static Ocorrencia ocorrencia;
    private static OcorrenciaRequest ocorrenciaRequest;

    @BeforeAll
    public static void setUp() {
        ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao("Teste");
        ocorrencia.setDataHora(LocalDateTime.parse("15-09-2022 19:45:00"));
        ocorrencia.setAluno(null);

        ocorrenciaRequest = new OcorrenciaRequest();
        ocorrenciaRequest.setDescricao("Teste de requisição");
        ocorrenciaRequest.setDataHora(LocalDateTime.parse("15-09-2022 19:45:00"));
        ocorrenciaRequest.setAlunoId(null);
    }

    @AfterEach
    void tearDown() {
        ocorrenciaRepository.deleteAll();
    }

    @DisplayName("Busca as ocorrências no banco")
    @Test
    void buscarOcorrencias() {
        ocorrenciaRepository.save(ocorrencia);
        List<OcorrenciaResponse> ocorrenciaList = ocorrenciaService.buscarOcorrencias();

        Assertions.assertEquals(1, ocorrenciaList.size());
    }

    @DisplayName("Busca uma ocorrência no banco")
    @Test
    void buscarOcorrencia() {
       Ocorrencia ocorrenciaLocal = ocorrenciaRepository.save(ocorrencia);
       OcorrenciaResponse ocorrenciaResponse = ocorrenciaService.buscarOcorrencia(ocorrenciaLocal.getId());

       Assertions.assertEquals(ocorrenciaLocal.getId(), ocorrenciaResponse.getId());
    }

    @DisplayName("Registra uma ocorrência")
    @Test
    void registrarOcorrencia() {
        OcorrenciaResponse ocorrenciaResponse = ocorrenciaService.registrarOcorrencia(ocorrenciaRequest);
    }

    @Test
    void alterarOcorrencia() {
    }

    @Test
    void deletarOcorrencia() {
    }
}