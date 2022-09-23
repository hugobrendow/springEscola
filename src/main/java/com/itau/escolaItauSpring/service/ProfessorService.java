package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.ProfessorRequest;
import com.itau.escolaItauSpring.dto.response.ProfessorResponse;
import com.itau.escolaItauSpring.enums.NivelProfessorEnum;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.mapper.ProfessorMapper;
import com.itau.escolaItauSpring.model.Professor;
import com.itau.escolaItauSpring.model.Usuario;
import com.itau.escolaItauSpring.repository.ProfessorRepository;
import com.itau.escolaItauSpring.security.SecurityConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorMapper mapper;
    private final ProfessorRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ProfessorResponse adicionar(ProfessorRequest professorRequest){
        Usuario usuario = professorRequest.getUsuario();
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Professor professor = mapper.toModel(professorRequest);
        return mapper.toResponse(repository.save(professor));
    }

    public List<ProfessorResponse> listar(Pageable pageable) {
        Page<Professor> professores = repository.findAll(pageable);
        return mapper.toResponseList(professores.getContent());
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

    public List<ProfessorResponse> filtro(String nome, String cpf, NivelProfessorEnum nivel, Pageable pageable) {
        Page<Professor> professoresPage = repository.findProfessorByNomeContainingIgnoreCaseOrCpfContainingOrNivel(nome, cpf, nivel, pageable);
        return mapper.toResponseList(professoresPage.getContent());
    }
    public void limparBanco() {
        repository.deleteAll();
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
