package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.UsuarioPerfilRequest;
import com.itau.escolaItauSpring.dto.response.UsuarioPerfilResponse;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.mapper.UsuarioPerfilMapper;
import com.itau.escolaItauSpring.model.Perfil;
import com.itau.escolaItauSpring.model.Usuario;
import com.itau.escolaItauSpring.model.UsuarioPerfil;
import com.itau.escolaItauSpring.repository.UsuarioPerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioPerfilService {

    private final UsuarioPerfilRepository repository;
    private final UsuarioPerfilMapper mapper;

    public UsuarioPerfilResponse criar(Usuario usuario, Perfil perfil) {
        UsuarioPerfil perfilUsuario = new UsuarioPerfil(null, usuario, perfil);
        return mapper.toResponse(repository.save(perfilUsuario));
    }

    public void remover(UsuarioPerfilRequest usuarioPerfilRequest) {
        UsuarioPerfil usuarioPerfil = repository
                .findByUsuarioIdAndPerfilId(usuarioPerfilRequest.getUsuarioId(), usuarioPerfilRequest.getPerfilId())
                .orElseThrow(() -> new ItemNaoExistenteException());
        repository.delete(usuarioPerfil);
    }
}
