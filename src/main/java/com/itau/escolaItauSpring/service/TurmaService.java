package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.TurmaRequest;
import com.itau.escolaItauSpring.dto.response.TurmaResponse;
import com.itau.escolaItauSpring.mapper.TurmaMapper;
import com.itau.escolaItauSpring.model.Curso;
import com.itau.escolaItauSpring.model.Turma;
import com.itau.escolaItauSpring.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final CursoService cursoService;
    private final TurmaMapper turmaMapper;

    public TurmaResponse criar(TurmaRequest turmaRequest) {
        Curso curso = cursoService.findById(turmaRequest.getCurso().getId());
        Turma turma = turmaMapper.toModel(turmaRequest);
        turma.setCurso(curso);
        return turmaMapper.toResponse(turmaRepository.save(turma));
    }
}
