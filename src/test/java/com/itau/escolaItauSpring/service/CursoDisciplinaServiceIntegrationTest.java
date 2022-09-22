package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.ModelFactory;
import com.itau.escolaItauSpring.model.Curso;
import com.itau.escolaItauSpring.model.CursoDisciplina;
import com.itau.escolaItauSpring.model.Disciplina;
import com.itau.escolaItauSpring.repository.CursoDisciplinaRepository;
import com.itau.escolaItauSpring.repository.CursoRepository;
import com.itau.escolaItauSpring.repository.DisciplinaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CursoDisciplinaServiceIntegrationTest {

    @Autowired
    CursoDisciplinaService cursoDisciplinaService;

    @Autowired
    CursoDisciplinaRepository cursoDisciplinaRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Test
    public void buscarPorIdTest() {
        Curso curso = ModelFactory.curso();
        curso.setId(null);
        Disciplina disciplina = ModelFactory.disciplina();
        disciplina.setId(null);
        cursoRepository.save(curso);
        disciplinaRepository.save(disciplina);
        CursoDisciplina cursoDisciplina = new CursoDisciplina();
        cursoDisciplina.setCurso(curso);
        cursoDisciplina.setDisciplina(disciplina);

        cursoDisciplinaRepository.save(cursoDisciplina);
        Assertions.assertEquals(cursoDisciplinaService.buscarPorId(cursoDisciplina.getId()).getId(), cursoDisciplina.getId());
    }

}
