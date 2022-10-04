package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.VinculaCursoRequest;
import com.itau.escolaItauSpring.dto.response.VinculaCursoResponse;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.mapper.ProfessorCursoDisciplinaMapper;
import com.itau.escolaItauSpring.model.CursoDisciplina;
import com.itau.escolaItauSpring.model.Professor;
import com.itau.escolaItauSpring.model.ProfessorCursoDisciplina;
import com.itau.escolaItauSpring.repository.ProfessorCursoDisciplinaRepository;
import com.itau.escolaItauSpring.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorCursoDisciplinaService {

    private final ProfessorCursoDisciplinaRepository repository;
    private final ProfessorRepository professorRepository;
    private final CursoDisciplinaService cursoDisciplinaService;
    private final ProfessorCursoDisciplinaMapper professorCursoDisciplinaMapper;

    public VinculaCursoResponse vincularCurso(VinculaCursoRequest vinculaCursoRequest) {
        Professor professor = professorRepository.findById(vinculaCursoRequest.getProfessorId()).orElseThrow(ItemNaoExistenteException::new);
        CursoDisciplina cursoDisciplina = cursoDisciplinaService.buscarModelPorId(vinculaCursoRequest.getCursoDisciplinaId());

        ProfessorCursoDisciplina professorCursoDisciplina = ProfessorCursoDisciplina.builder()
                .professor(professor)
                .cursoDisciplina(cursoDisciplina)
                .titular(vinculaCursoRequest.getTitular())
                .build();

        repository.save(professorCursoDisciplina);

        return professorCursoDisciplinaMapper.toResponse(professorCursoDisciplina);
    }
}
