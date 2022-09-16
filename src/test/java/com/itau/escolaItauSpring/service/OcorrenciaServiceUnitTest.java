package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.OcorrenciaRequest;
import com.itau.escolaItauSpring.dto.response.OcorrenciaResponse;
import com.itau.escolaItauSpring.mapper.OcorrenciaMapper;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.model.Ocorrencia;
import com.itau.escolaItauSpring.repository.AlunoRepository;
import com.itau.escolaItauSpring.repository.OcorrenciaRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class OcorrenciaServiceUnitTest {

    @InjectMocks
    private OcorrenciaService ocorrenciaService;

    @Mock
    OcorrenciaRepository ocorrenciaRepository;

    @Mock
    AlunoRepository alunoRepository;

    @Mock
    OcorrenciaMapper ocorrenciaMapper;

    private static Ocorrencia ocorrencia;
    private static OcorrenciaResponse ocorrenciaResponse;
    private static OcorrenciaRequest ocorrenciaRequest;

    @BeforeEach
    void setUp() {
        ocorrencia = new Ocorrencia();
        ocorrenciaRequest = new OcorrenciaRequest();
        ocorrenciaResponse = new OcorrenciaResponse();
        ocorrencia.setId(UUID.randomUUID());
        ocorrencia.setAluno(null);
    }

    @AfterEach
    void tearDown() {
        ocorrencia = null;
    }

    @DisplayName("Busca as ocorrencias")
    @Test
    void testBuscarOcorrencias() {
        Mockito.when(ocorrenciaRepository.findAll()).thenReturn(List.of(ocorrencia));
        Mockito.when(ocorrenciaMapper.toResponseList(ArgumentMatchers.any())).thenReturn(List.of(ocorrenciaResponse));

        List<OcorrenciaResponse> ocorrenciaResponseList = ocorrenciaService.buscarOcorrencias();

        Assertions.assertEquals(1, ocorrenciaResponseList.size());
    }

    @DisplayName("Busca uma ocorrência específica")
    @Test
    void testBuscarOcorrenciaPorId() {
        Mockito.when(ocorrenciaRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(ocorrencia));
        Mockito.when(ocorrenciaMapper.toResponse(ArgumentMatchers.any())).thenReturn(ocorrenciaResponse);

        OcorrenciaResponse ocorrenciaLocalizada = ocorrenciaService.buscarOcorrencia(ocorrencia.getId());

        Assertions.assertEquals(ocorrenciaResponse, ocorrenciaLocalizada);

        Mockito.verify(ocorrenciaRepository, Mockito.times(1)).findById(ocorrencia.getId());

    }

    @DisplayName("Registra uma ocorrência")
    @Test
    void testRegistrarUmaOcorrencia() {
        Mockito.when(ocorrenciaRepository.save(ocorrencia)).thenReturn(ocorrencia);
        Mockito.when(ocorrenciaMapper.toResponse(ocorrencia)).thenReturn(ocorrenciaResponse);
        Mockito.when(ocorrenciaMapper.toModel(ocorrenciaRequest)).thenReturn(ocorrencia);
        Mockito.when(alunoRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Aluno()));

        OcorrenciaResponse ocorrenciaRegistrada = ocorrenciaService.registrarOcorrencia(ocorrenciaRequest);

        Assertions.assertEquals(ocorrenciaResponse, ocorrenciaRegistrada);

        Mockito.verify(ocorrenciaRepository, Mockito.times(1)).save(ocorrencia);
    }

    @DisplayName("Altera uma ocorrência")
    @Test
    void testAlterarOcorrencia() {
        Mockito.when(ocorrenciaRepository.save(ocorrencia)).thenReturn(ocorrencia);
        Mockito.when(ocorrenciaMapper.toResponse(ocorrencia)).thenReturn(ocorrenciaResponse);
        Mockito.when(ocorrenciaMapper.toModel(ocorrenciaRequest)).thenReturn(ocorrencia);
        Mockito.when(alunoRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Aluno()));

        OcorrenciaResponse ocorrenciaAlterada = ocorrenciaService.alterarOcorrencia(UUID.randomUUID(), ocorrenciaRequest);

        Assertions.assertEquals(ocorrenciaResponse, ocorrenciaAlterada);

        Mockito.verify(ocorrenciaRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @DisplayName("Deleta uma ocorrência")
    @Test
    void testDeletarOcorrencia() {
        ocorrenciaService.deletarOcorrencia(ocorrencia.getId());
        Mockito.verify(ocorrenciaRepository, Mockito.times(1)).deleteById(ocorrencia.getId());
    }

}
