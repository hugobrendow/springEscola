package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.PerfilRequest;
import com.itau.escolaItauSpring.dto.response.PerfilResponse;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.mapper.PerfilMapper;
import com.itau.escolaItauSpring.model.Perfil;
import com.itau.escolaItauSpring.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository repository;

    private final PerfilMapper mapper;

    public PerfilResponse criar(PerfilRequest perfilRequest) {
        Perfil perfil = mapper.toModel(perfilRequest);
        return mapper.toResponse(repository.save(perfil));
    }

    public Page<PerfilResponse> listar(Pageable pageable) {
        Page<Perfil> perfis = repository.findAll(pageable);
        List<PerfilResponse> perfilResponses = mapper.toResponseList(perfis.getContent());
        return new PageImpl<>(perfilResponses, pageable, perfilResponses.size());
    }

    public List<PerfilResponse> buscarPorUsuarioId(UUID usuarioId) {
        List<Perfil> perfis = repository.findByUsuarioPerfis_UsuarioId(usuarioId);
        return mapper.toResponseList(perfis);
    }

    public void remover(UUID id) {
        if (!repository.existsById(id)) {
            throw new ItemNaoExistenteException();
        }
        repository.deleteById(id);
    }

    public Perfil buscarPorId(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ItemNaoExistenteException("Perfil não encontrado"));
    }
}
