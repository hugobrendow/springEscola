package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.OcorrenciaRequest;
import com.itau.escolaItauSpring.dto.response.OcorrenciaResponse;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.model.Ocorrencia;
import com.itau.escolaItauSpring.repository.AlunoRepository;
import com.itau.escolaItauSpring.repository.OcorrenciaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OcorrenciaServiceIntegrationTest {

    private final OcorrenciaRepository ocorrenciaRepository;
    private final OcorrenciaService ocorrenciaService;
    private final AlunoRepository alunoRepository;

    @Autowired
    public OcorrenciaServiceIntegrationTest(OcorrenciaRepository ocorrenciaRepository, OcorrenciaService ocorrenciaService, AlunoRepository alunoRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.ocorrenciaService = ocorrenciaService;
        this.alunoRepository = alunoRepository;
    }

    private static Ocorrencia ocorrencia;
    private static OcorrenciaRequest ocorrenciaRequest;
    private static Aluno aluno;

    @BeforeAll
    public static void setUp() {
        ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao("Teste");
        ocorrencia.setDataHora(LocalDateTime.of(2022, 9, 15, 15, 15, 15));
        ocorrencia.setAluno(null);

        ocorrenciaRequest = new OcorrenciaRequest();
        ocorrenciaRequest.setDescricao("Teste de requisição");
        ocorrenciaRequest.setAlunoId(null);

        aluno = new Aluno();
        aluno.setNome("José");
        aluno.setCpf("12345678911");
        aluno.setEmail("test@test.com");
        aluno.setTelefone("12920000002");
        aluno.setEndereco(null);
        aluno.setDataNascimento(LocalDate.of(1990, 10, 10));
    }

    @BeforeEach
    void setUpEach(){
        aluno = alunoRepository.save(aluno);
        ocorrencia = ocorrenciaRepository.save(ocorrencia);
        ocorrenciaRequest.setAlunoId(aluno.getId());
    }

    @AfterEach
    void tearDown() {
        ocorrenciaRepository.delete(ocorrencia);
        alunoRepository.delete(aluno);
    }

    @DisplayName("Busca as ocorrências no banco")
    @Test
    void testBuscarOcorrencias() {
        List<OcorrenciaResponse> ocorrenciaList = ocorrenciaService.buscarOcorrencias();

        Assertions.assertEquals(1, ocorrenciaList.size());
    }

    @DisplayName("Busca uma ocorrência no banco")
    @Test
    void testBuscarOcorrencia() {
        OcorrenciaResponse ocorrenciaResponse = ocorrenciaService.buscarOcorrencia(ocorrencia.getId());

        Assertions.assertEquals(ocorrencia.getId(), ocorrenciaResponse.getId());
    }

    @DisplayName("Registra uma ocorrência")
    @Test
    void testRegistrarOcorrencia() {

        OcorrenciaResponse ocorrenciaSalva = ocorrenciaService.registrarOcorrencia(ocorrenciaRequest);

        Assertions.assertNotNull(ocorrenciaSalva.getId());
        Assertions.assertEquals(aluno.getId(), ocorrenciaSalva.getAluno().getId());

        ocorrenciaRepository.deleteById(ocorrenciaSalva.getId());
    }

    @DisplayName("Altera uma ocorrência no banco")
    @Test
    void testAlterarOcorrencia() {
        Assertions.assertNull(ocorrencia.getAluno());

        OcorrenciaResponse ocorrenciaResponse = ocorrenciaService.alterarOcorrencia(ocorrencia.getId(), ocorrenciaRequest);

        Assertions.assertEquals(ocorrenciaRequest.getAlunoId(), ocorrenciaResponse.getAluno().getId());
    }

    @DisplayName("Apaga uma ocorrência no banco")
    @Test
    void testDeletarOcorrencia() {
        ocorrenciaService.deletarOcorrencia(ocorrencia.getId());

        Assertions.assertFalse(ocorrenciaRepository.findById(ocorrencia.getId()).isPresent());
    }
}