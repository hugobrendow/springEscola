package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.mapper.MatriculaMapper;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.model.Matricula;
import com.itau.escolaItauSpring.model.Turma;
import com.itau.escolaItauSpring.repository.MatriculaRepository;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
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

    @Test
    void matricular() {
        Mockito.when(alunoService.buscarModelPorId(any())).thenReturn(new Aluno());
        Mockito.when(turmaService.buscarModelPorId(any())).thenReturn(new Turma());

        Mockito.when(matriculaRepository.findByAlunoIdAndTurmaId(any(),any())).thenReturn(Optional.of(new Matricula()));
        Mockito.when(turmaService.verificarSeHaVagas(any())).thenReturn(true);
    }

    @Test
    void buscarModelPorId() {
    }

    @Test
    void buscarPorId() {
    }

    @Test
    void listarPorTurma() {
    }

    @Test
    void listarPorAluno() {
    }

    @Test
    void trancarMatricula() {
    }

    @Test
    void ativarMatricula() {
    }

    @Test
    void cancelarMatricula() {
    }
}