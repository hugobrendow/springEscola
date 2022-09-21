package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.MatriculaRequest;
import com.itau.escolaItauSpring.dto.response.MatriculaResponse;
import com.itau.escolaItauSpring.enums.StatusMatricula;
import com.itau.escolaItauSpring.exception.AlunoJaMatriculadoException;
import com.itau.escolaItauSpring.exception.NaoHaVagasException;
import com.itau.escolaItauSpring.mapper.MatriculaMapper;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.model.Matricula;
import com.itau.escolaItauSpring.model.Turma;
import com.itau.escolaItauSpring.repository.MatriculaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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
    }
    @Test
    void testeMatricular() {
        when(alunoService.buscarModelPorId(any())).thenReturn(new Aluno());
        when(turmaService.buscarModelPorId(any())).thenReturn(new Turma());

        when(matriculaRepository.findByAlunoIdAndTurmaId(any(),any())).thenReturn(Optional.empty());
        when(turmaService.verificarSeHaVagas(any())).thenReturn(true);

        when(matriculaRepository.save(any())).thenReturn(new Matricula());
        doNothing().when(turmaService).atualizarVagas(any());
        when(mapper.toResponse(any())).thenReturn(new MatriculaResponse());

        MatriculaResponse resultado = matriculaService.matricular(new MatriculaRequest());

        assertInstanceOf(MatriculaResponse.class, resultado);
        verify(turmaService,times(1)).atualizarVagas(any());

    }

    @Test
    void testNaoHaVagasException(){
        when(alunoService.buscarModelPorId(any())).thenReturn(new Aluno());
        when(turmaService.buscarModelPorId(any())).thenReturn(new Turma());
        when(matriculaRepository.findByAlunoIdAndTurmaId(any(),any())).thenReturn(Optional.empty());
        when(turmaService.verificarSeHaVagas(any())).thenReturn(false);

        assertThrows(NaoHaVagasException.class,
                () -> matriculaService.matricular(new MatriculaRequest()));
        verify(turmaService,times(1)).verificarSeHaVagas(any());
    }

    @Test
    void AlunoJaMatriculadoException(){
        when(alunoService.buscarModelPorId(any())).thenReturn(new Aluno());
        when(turmaService.buscarModelPorId(any())).thenReturn(new Turma());
        when(matriculaRepository.findByAlunoIdAndTurmaId(any(),any())).thenReturn(Optional.of(new Matricula()));

        assertThrows(AlunoJaMatriculadoException.class,
                () -> matriculaService.matricular(new MatriculaRequest()));
        verify(matriculaRepository,times(1)).findByAlunoIdAndTurmaId(any(), any());
    }

    @Test
    void testeBuscarModelPorId() {
        when(matriculaRepository.findById(any())).thenReturn(Optional.of(new Matricula()));

        Matricula resultado = matriculaService.buscarModelPorId(UUID.randomUUID());

        assertInstanceOf(Matricula.class, resultado);
        assertNotNull(resultado);
    }

    @Test
    void testeBuscarPorId() {
        when(matriculaRepository.findById(any())).thenReturn(Optional.of(new Matricula()));
        when(mapper.toResponse(any())).thenReturn(new MatriculaResponse());

        MatriculaResponse resultado = matriculaService.buscarPorId(UUID.randomUUID());

        assertInstanceOf(MatriculaResponse.class, resultado);
        assertNotNull(resultado);
    }

    @Test
    void testeListarPorTurma() {
        Pageable pageable = PageRequest.of(0, 12);

        when(matriculaRepository.findAllByTurmaId(any(), any())).thenReturn(new PageImpl<>(List.of(new Matricula())));
        when(mapper.toResponseList(List.of(new Matricula()))).thenReturn(List.of(new MatriculaResponse()));

        Page<MatriculaResponse> resultado = matriculaService.listarPorTurmaPaginada(pageable, UUID.randomUUID());

        assertNotNull(resultado);
    }

    @Test
    void testeListarPorAluno() {
        when(matriculaRepository.findAllByAlunoId(any())).thenReturn(List.of(new Matricula()));
        when(mapper.toResponseList(List.of(new Matricula()))).thenReturn(List.of(new MatriculaResponse()));

        List<MatriculaResponse> resultado = matriculaService.listarPorAluno(UUID.randomUUID());

        assertNotNull(resultado);
        assertInstanceOf(MatriculaResponse.class, resultado.get(0));
    }

    @Test
    void testeTrancarMatricula() {
        MatriculaResponse matriculaTrancada= new MatriculaResponse();
        matriculaTrancada.setStatus(StatusMatricula.TRANCADA);

        when(matriculaRepository.findById(any())).thenReturn(Optional.of(new Matricula()));
        when(matriculaRepository.save(any())).thenReturn(new Matricula());
        when(mapper.toResponse(any())).thenReturn(matriculaTrancada);

        MatriculaResponse resultado = matriculaService.trancarMatricula(UUID.randomUUID());

        assertEquals(StatusMatricula.TRANCADA, resultado.getStatus());
        verify(matriculaRepository,times(1)).findById(any(UUID.class));
        verify(matriculaRepository,times(1)).save(any(Matricula.class));
        verify(mapper,times(1)).toResponse(any(Matricula.class));
    }

    @Test
    void testeAtivarMatricula() {
        MatriculaResponse matriculaAtivada = new MatriculaResponse();
        matriculaAtivada.setStatus(StatusMatricula.ATIVADA);

        when(matriculaRepository.findById(any())).thenReturn(Optional.of(new Matricula()));
        when(matriculaRepository.save(any())).thenReturn(new Matricula());
        when(mapper.toResponse(any())).thenReturn(matriculaAtivada);

        MatriculaResponse resultado = matriculaService.ativarMatricula(UUID.randomUUID());

        assertEquals(StatusMatricula.ATIVADA, resultado.getStatus());
        verify(matriculaRepository,times(1)).findById(any(UUID.class));
        verify(matriculaRepository,times(1)).save(any(Matricula.class));
        verify(mapper,times(1)).toResponse(any(Matricula.class));


    }

    @Test
    void testeCancelarMatricula() {

        MatriculaResponse matriculaCancelada = new MatriculaResponse();
        matriculaCancelada.setStatus(StatusMatricula.CANCELADA);

        when(matriculaRepository.findById(any())).thenReturn(Optional.of(new Matricula()));
        when(matriculaRepository.save(any())).thenReturn(new Matricula());
        when(mapper.toResponse(any())).thenReturn(matriculaCancelada);

        MatriculaResponse resultado = matriculaService.cancelarMatricula(UUID.randomUUID());

        assertEquals(StatusMatricula.CANCELADA, resultado.getStatus());
        verify(matriculaRepository,times(1)).findById(any(UUID.class));
        verify(matriculaRepository,times(1)).save(any(Matricula.class));
        verify(mapper,times(1)).toResponse(any(Matricula.class));
    }
}