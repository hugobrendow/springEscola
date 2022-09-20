package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.mapper.ProfessorCursoDisciplinaMapper;
import com.itau.escolaItauSpring.repository.ProfessorCursoDisciplinaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProfessorCursoDisciplinaServiceTest {
    @InjectMocks
    ProfessorCursoDisciplinaService professorCursoDisciplinaService;

    @Mock
    ProfessorCursoDisciplinaMapper professorCursoDisciplinaMapper;

    @Mock
    ProfessorCursoDisciplinaRepository professorCursoDisciplinaRepository;

    @Test
    @DisplayName("Deve vincular um professor a uma disciplina")
    public void vincularCurso(){

    }

}
