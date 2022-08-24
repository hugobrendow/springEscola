package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.AlunoRequest;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.repository.AlunoRepository;
import com.itau.escolaItauSpring.repository.MemoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlunoService {
    private MemoryRepository<UUID, Aluno> repository = new AlunoRepository();

    public Aluno adicionar(AlunoRequest alunoRequest) {
        Aluno aluno = new Aluno(alunoRequest);
        return repository.adicionar(aluno);
    }

    public void ativar(Aluno aluno) throws ItemNaoExistenteException {
        aluno.setAtivado(true);
        repository.alterar(aluno.getId(), aluno);
    }

    public void desativar(Aluno aluno) throws ItemNaoExistenteException {
        aluno.setAtivado(false);
        repository.alterar(aluno.getId(), aluno);
    }

    public List<Aluno> listar() {
        return repository.listar();
    }

    public Aluno localizar(UUID id) {
        return repository.localizar(id);
    }
}
