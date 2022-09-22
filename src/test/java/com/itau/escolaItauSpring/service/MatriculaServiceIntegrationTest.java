package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.MatriculaRequest;
import com.itau.escolaItauSpring.dto.response.MatriculaResponse;
import com.itau.escolaItauSpring.enums.StatusMatricula;
import com.itau.escolaItauSpring.exception.AlunoJaMatriculadoException;
import com.itau.escolaItauSpring.mapper.MatriculaMapper;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.model.Turma;
import com.itau.escolaItauSpring.repository.MatriculaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

@SpringBootTest
class MatriculaServiceIntegrationTest {

    @Autowired
    MatriculaService matriculaService;

    @Autowired
    MatriculaRepository matriculaRepository;

    @Autowired
    AlunoService alunoService;

    @Autowired
    TurmaService turmaService;

    @Autowired
    MatriculaMapper mapper;

    @Test
    public void testMatricularAluno() {
        UUID idAluno = UUID.fromString("d014f344-382f-11ed-a261-0242ac120006");
        UUID idTurma = UUID.fromString("d014f344-382f-11ed-a261-0242ac120003");

        MatriculaRequest request = new MatriculaRequest();
        request.setIdTurma(idTurma);
        request.setIdAluno(idAluno);

        MatriculaResponse resultado = matriculaService.matricular(request);

        Assertions.assertNotNull(resultado.getId());
        Assertions.assertEquals(resultado.getIdAluno(), idAluno);
        Assertions.assertEquals(resultado.getIdTurma(), idTurma);
        Assertions.assertEquals(resultado.getStatus(), StatusMatricula.ATIVADA);

    }

}