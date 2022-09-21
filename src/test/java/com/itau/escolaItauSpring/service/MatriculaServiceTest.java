package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.MatriculaRequest;
import com.itau.escolaItauSpring.dto.response.MatriculaResponse;
import com.itau.escolaItauSpring.mapper.MatriculaMapper;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.model.Matricula;
import com.itau.escolaItauSpring.model.Turma;
import com.itau.escolaItauSpring.repository.MatriculaRepository;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;


class MatriculaServiceTest {
    @InjectMocks
    private MatriculaService matriculaService;

    @Mock
    private MatriculaRepository matriculaRepository;
    @Mock
    private AlunoService alunoService;
    @Mock
    private TurmaService turmaService;
    @Mock
    private MatriculaMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        carregarClasses();
    }
    @Test
    void testeMatricular() {
        Mockito.when(alunoService.buscarModelPorId(any())).thenReturn(new Aluno());
        Mockito.when(turmaService.buscarModelPorId(any())).thenReturn(new Turma());

        Mockito.when(matriculaRepository.findByAlunoIdAndTurmaId(any(),any())).thenReturn(Optional.empty());
        Mockito.when(turmaService.verificarSeHaVagas(any())).thenReturn(true);

        Mockito.when(matriculaRepository.save(any())).thenReturn(new Matricula());
        Mockito.doNothing().when(turmaService).atualizarVagas(any());

        Mockito.when(mapper.toResponse(any())).thenReturn(new MatriculaResponse());

        var resultadoTeste = matriculaService.matricular(new MatriculaRequest());
        Assertions.assertInstanceOf(MatriculaResponse.class, resultadoTeste);

        Mockito.verify(turmaService,times(1)).atualizarVagas(any());
    }

    @Test
    void testeBuscarModelPorId() {
    }

    @Test
    void testeBuscarPorId() {
    }

    @Test
    void testeListarPorTurma() {
    }

    @Test
    void testeListarPorAluno() {
    }

    @Test
    void testeTrancarMatricula() {
    }

    @Test
    void testeAtivarMatricula() {
    }

    @Test
    void testeCancelarMatricula() {
    }

    void carregarClasses(){
        Matricula matricula = new Matricula();
    }
}