package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.NotaRequest;
import com.itau.escolaItauSpring.dto.response.NotaResponse;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.mapper.NotaMapper;
import com.itau.escolaItauSpring.model.CursoDisciplina;
import com.itau.escolaItauSpring.model.Matricula;
import com.itau.escolaItauSpring.model.Nota;
import com.itau.escolaItauSpring.repository.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotaService {

    private final NotaMapper mapper;
    private final NotaRepository repository;
    private final  MatriculaService matriculaService;
    private final CursoDisciplinaService cursoDisciplinaService;

    public NotaResponse adicionar(NotaRequest notaRequest) {
        Matricula matricula = matriculaService.buscarModelPorId(notaRequest.getMatriculaId());
        CursoDisciplina cursoDisciplina = cursoDisciplinaService.buscarModelPorId(notaRequest.getCursoDisciplinaId());

        Nota nota = mapper.toModel(notaRequest, matricula, cursoDisciplina);
        return mapper.toResponse(repository.save(nota));
    }

    public List<NotaResponse> listar() {
        return mapper.toResponseList(repository.findAll());
    }

    public NotaResponse buscarPorId(UUID id) {
        Nota nota = buscarModelPorId(id);
        return mapper.toResponse(nota);
    }

    public NotaResponse atualizar(UUID id, NotaRequest notaRequest) {
        Nota nota = buscarModelPorId(id);
        nota.setNota(notaRequest.getNota());
        repository.save(nota);
        return mapper.toResponse(nota);
    }

    public void remover(UUID id) {
        this.buscarModelPorId(id);
        repository.deleteById(id);
    }

    public Nota buscarModelPorId(UUID id){
        return repository.findById(id)
                .orElseThrow(ItemNaoExistenteException::new);
    }

    public List<NotaResponse> listarPorMatricula(UUID id) {
        Matricula matricula = matriculaService.buscarModelPorId(id);
        return mapper.toResponseList(repository.findByMatriculaId(matricula.getId()));
    }

    public List<NotaResponse> listarPorCursoDisciplina(UUID id) {
        CursoDisciplina cursoDisciplina = cursoDisciplinaService.buscarModelPorId(id);
        return mapper.toResponseList(repository.findByCursoDisciplinaId(cursoDisciplina.getId()));
    }


}
