package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.ProfessorRequest;
import com.itau.escolaItauSpring.dto.response.ProfessorResponse;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.mapper.ProfessorMapper;
import com.itau.escolaItauSpring.model.Professor;
import com.itau.escolaItauSpring.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorMapper mapper;
    private final ProfessorRepository repository;

    public ProfessorResponse adicionar(ProfessorRequest professorRequest){
        Professor professor = mapper.toModel(professorRequest);
        professor = repository.save(professor);
        return mapper.toResponse(professor);
    }

    public List<ProfessorResponse> listar() {
        return mapper.toResponseList(repository.findAll());
    }

    public void removerProfessor(UUID id) {
        Professor professor = buscarProfessorPorId(id);
        repository.delete(professor);
    }

    public ProfessorResponse buscarPorId(UUID id) {
        Professor professor = buscarProfessorPorId(id);
        return mapper.toResponse(professor);
    }

    private Professor buscarProfessorPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(ItemNaoExistenteException::new);
    }

    public ProfessorResponse atualizar(UUID id, ProfessorRequest professorRequest){
        UUID idProfessor = buscarProfessorPorId(id).getId();
        Professor professor = mapper.toModel(professorRequest);
        professor.setId(idProfessor);
        repository.save(professor);
        return mapper.toResponse(professor);
    }

    public List<ProfessorResponse> buscarPorNome(String nome) {
        return mapper.toResponseList(repository.findByNomeLikeIgnoreCase("%" + nome +"%"));
    }
    
}
