package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.NotaRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.dto.response.CursoDisciplinaResponse;
import com.itau.escolaItauSpring.dto.response.DisciplinaResponse;
import com.itau.escolaItauSpring.dto.response.NotaResponse;
import com.itau.escolaItauSpring.mapper.NotaMapper;
import com.itau.escolaItauSpring.model.Nota;
import com.itau.escolaItauSpring.repository.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotaService {

    private final NotaMapper mapper;
    private final NotaRepository repository;
    private final  MatriculaService matriculaService;
    private final CursoDisciplinaService cursoDisciplinaService;

    public NotaResponse adicionar(NotaRequest notaRequest) {

        //TODO buscarID da Matricula e do CursoDisciplina
        CursoDisciplinaResponse cursoDisciplina = cursoDisciplinaService.buscarPorId(notaRequest.getCursoDisciplina());

        Nota nota  = mapper.toModel(notaRequest);
        return mapper.toResponse(repository.save(nota));

    }
}
