package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.AlunoRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.mapper.AlunoMapper;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoMapper mapper;
    private final AlunoRepository repository;

    public AlunoResponse adicionar(AlunoRequest alunoRequest) {
        Aluno aluno = mapper.toModel(alunoRequest);
        return mapper.toResponse(repository.save(aluno));
    }

    public List<AlunoResponse> listar() {
        return mapper.toResponseList(repository.findAll());
    }

    public AlunoResponse buscarPorId(UUID id) {
        Aluno aluno = repository.findById(id)
                .orElseThrow(ItemNaoExistenteException::new);
        return mapper.toResponse(aluno);
    }

    public void remover(UUID id) {
        this.buscarPorId(id);
        repository.deleteById(id);
    }
}
