package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.UsuarioPerfilRequest;
import com.itau.escolaItauSpring.dto.request.UsuarioRequest;
import com.itau.escolaItauSpring.dto.response.UsuarioPerfilResponse;
import com.itau.escolaItauSpring.dto.response.UsuarioResponse;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.mapper.UsuarioMapper;
import com.itau.escolaItauSpring.model.Perfil;
import com.itau.escolaItauSpring.model.Usuario;
import com.itau.escolaItauSpring.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;

    private final PerfilService perfilService;

    private final UsuarioPerfilService usuarioPerfilService;
    private final UsuarioMapper mapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return usuario.getUserDetails();
    }

    public UsuarioResponse criar(UsuarioRequest usuarioRequest) {
        Usuario usuario = mapper.toModel(usuarioRequest);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return mapper.toResponse(repository.save(usuario));
    }

    public Page<UsuarioResponse> lista(Pageable pageable) {
        Page<Usuario> usuarios = repository.findAll(pageable);
        List<UsuarioResponse> usuarioResponses = mapper.toResponseList(usuarios.getContent());
        return new PageImpl<>(usuarioResponses, pageable, usuarioResponses.size());
    }

    public UsuarioResponse atualizar(UUID id, UsuarioRequest usuarioRequest) {
        Usuario usuario = mapper.toModel(usuarioRequest);
        usuario.setId(id);
        return mapper.toResponse(repository.save(usuario));
    }

    public UsuarioPerfilResponse adicionarPerfil(UsuarioPerfilRequest usuarioPerfilRequest) {
        Usuario usuario = buscarPorId(usuarioPerfilRequest.getUsuarioId());
        Perfil perfil = perfilService.buscarPorId(usuarioPerfilRequest.getPerfilId());

        return usuarioPerfilService.criar(usuario, perfil);
    }

    public void removerPerfil(UsuarioPerfilRequest usuarioPerfilRequest) {
        usuarioPerfilService.remover(usuarioPerfilRequest);
    }

    public Usuario buscarPorId(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ItemNaoExistenteException("Usuario não encontrado"));
    }
}
