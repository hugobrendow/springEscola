package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.ModelFactory;
import com.itau.escolaItauSpring.dto.request.VinculaCursoRequest;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.mapper.ProfessorCursoDisciplinaMapper;
import com.itau.escolaItauSpring.model.CursoDisciplina;
import com.itau.escolaItauSpring.model.Professor;
import com.itau.escolaItauSpring.model.ProfessorCursoDisciplina;
import com.itau.escolaItauSpring.repository.ProfessorCursoDisciplinaRepository;
import com.itau.escolaItauSpring.repository.ProfessorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

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
