package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.ModelFactory;
import com.itau.escolaItauSpring.dto.request.VinculaCursoRequest;
import com.itau.escolaItauSpring.dto.response.VinculaCursoResponse;
import com.itau.escolaItauSpring.enums.NivelProfessorEnum;
import com.itau.escolaItauSpring.mapper.ProfessorCursoDisciplinaMapper;
import com.itau.escolaItauSpring.model.*;
import com.itau.escolaItauSpring.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class ProfessorCursoDisciplinaServiceIntegrationTest {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ProfessorCursoDisciplinaService professorCursoDisciplinaService;

    @Autowired
    private CursoDisciplinaRepository cursoDisciplinaRepository;

    @Autowired
    private ProfessorCursoDisciplinaRepository professorCursoDisciplinaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Test
    public void vincularCursoTest() {
        Professor professor = ModelFactory.professor();
        professorRepository.save(professor);

        Disciplina disciplina = ModelFactory.disciplina();
        Curso curso = ModelFactory.curso();

        disciplinaRepository.save(disciplina);
        cursoRepository.save(curso);

        CursoDisciplina cursoDisciplina = CursoDisciplina.builder()
                .disciplina(disciplina)
                .curso(curso)
                .build();

        cursoDisciplinaRepository.save(cursoDisciplina);

        VinculaCursoRequest vinculaCursoRequest = VinculaCursoRequest.builder()
                .cursoDisciplinaId(cursoDisciplina.getId())
                .professorId(professor.getId())
                .titular(true)
                .build();

        VinculaCursoResponse vinculaCursoResponse = professorCursoDisciplinaService.vincularCurso(vinculaCursoRequest);

        Optional<ProfessorCursoDisciplina> professorCursoDisciplina = professorCursoDisciplinaRepository.findOneByProfessorAndCursoDisciplina(professor, cursoDisciplina);

        Assertions.assertEquals(vinculaCursoResponse.getId(), professorCursoDisciplina.get().getId());
    }

}
