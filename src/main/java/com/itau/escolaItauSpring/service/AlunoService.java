package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.AlunoRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.mapper.AlunoMapper;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoMapper mapper;
    private final AlunoRepository repository;

    public AlunoResponse adicionar(@Valid AlunoRequest alunoRequest) {
        Aluno aluno = mapper.toModel(alunoRequest);
        return mapper.toResponse(repository.save(aluno));
    }
}
