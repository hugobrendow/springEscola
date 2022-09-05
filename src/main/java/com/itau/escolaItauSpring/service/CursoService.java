package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.CursoRequest;
import com.itau.escolaItauSpring.dto.response.CursoResponse;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.mapper.CursoMapper;
import com.itau.escolaItauSpring.model.Curso;
import com.itau.escolaItauSpring.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CursoService {
    private final CursoRepository repository;
    private final CursoMapper mapper;

    public CursoResponse criar(CursoRequest cursoRequest) {
        Curso curso = mapper.toModel(cursoRequest);
        return mapper.toResponse(repository.save(curso));
    }

    public List<CursoResponse> listar() {
        List<Curso> cursos = repository.findAll();
        return mapper.toResponseList(cursos);
    }

    public CursoResponse buscarPorId(UUID id) {
        return mapper.toResponse(findById(id));
    }

    public void remover(UUID id) {
//        boolean exists = repository.existsById(id);
//        if (!exists) {
//            throw new ItemNaoExistenteException();
//        }
//        repository.delete(findById(id));
        repository.deleteById(id);
    }

    private Curso findById(UUID id) {
        return repository.findById(id).orElseThrow(ItemNaoExistenteException::new);
    }
}
