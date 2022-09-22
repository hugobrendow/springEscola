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
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfessorCursoDisciplinaServiceTest {
    @InjectMocks
    ProfessorCursoDisciplinaService professorCursoDisciplinaService;

    @Spy
    ProfessorCursoDisciplinaMapper professorCursoDisciplinaMapper = Mappers.getMapper(ProfessorCursoDisciplinaMapper.class);

    @Mock
    ProfessorCursoDisciplinaRepository professorCursoDisciplinaRepository;

    @Mock
    CursoDisciplinaService cursoDisciplinaService;

    @Mock
    ProfessorRepository professorRepository;

    @Test
    @DisplayName("Ao Buscar Professor Inexistente Por Id Lancar ItemNaoExistenteException")
    void aoBuscarProfessorInexistentePorIdLancarItemNaoExistenteException() {
        VinculaCursoRequest vinculaCursoRequest = ModelFactory.vinculaCursoRequest();

        when(professorRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ItemNaoExistenteException.class, () -> professorCursoDisciplinaService.vincularCurso(vinculaCursoRequest));
    }

    @Test
    @DisplayName("Deve vincular um professor a uma disciplina")
    public void aoVincularCursoRetornaSucesso(){
        Professor professor = ModelFactory.professor();
        CursoDisciplina cursoDisciplina = ModelFactory.cursoDisciplina();
        when(professorRepository.findById(any())).thenReturn(Optional.of(professor));
        when(cursoDisciplinaService.buscarModelPorId(any())).thenReturn(cursoDisciplina);
        when(professorCursoDisciplinaRepository.save(any())).thenReturn(new ProfessorCursoDisciplina());
        when(professorCursoDisciplinaMapper.toResponse(any())).thenCallRealMethod();

        var result = professorCursoDisciplinaService.vincularCurso(ModelFactory.vinculaCursoRequest());

        assertEquals(result.getProfessor().getId(), professor.getId());
        assertEquals(result.getCursoDisciplina().getId(), cursoDisciplina.getId());
    }

}
