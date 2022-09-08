package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.CursoRequest;
import com.itau.escolaItauSpring.dto.response.CursoResponse;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.mapper.CursoMapper;
import com.itau.escolaItauSpring.model.Curso;
import com.itau.escolaItauSpring.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Page<CursoResponse> listar(Pageable pageable) {
        Page<Curso> cursos = repository.findAll(pageable);
        List<CursoResponse> cursoResponses = mapper.toResponseList(cursos.getContent());
        return new PageImpl<>(cursoResponses, pageable, cursoResponses.size());
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

    public List<CursoResponse> filtrar(String nome, Pageable pageable) {
        Page<Curso> cursosPage = repository.findByNome(nome, pageable);
        return mapper.toResponseList(cursosPage.getContent());
    }
    private Curso findById(UUID id) {
        return repository.findById(id).orElseThrow(ItemNaoExistenteException::new);
    }
}
