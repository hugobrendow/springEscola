package com.itau.escolaItauSpring.controller;

import com.itau.escolaItauSpring.dto.request.UsuarioPerfilRequest;
import com.itau.escolaItauSpring.dto.request.UsuarioRequest;
import com.itau.escolaItauSpring.dto.response.UsuarioPerfilResponse;
import com.itau.escolaItauSpring.dto.response.UsuarioResponse;
import com.itau.escolaItauSpring.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@Valid @RequestBody UsuarioRequest request, UriComponentsBuilder uriComponentsBuilder) {
        UsuarioResponse usuario = service.criar(request);
        URI uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> listar(@PageableDefault(size = 5, page = 0, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UsuarioResponse> usuarios = service.lista(pageable);
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") UUID id, @Valid @RequestBody UsuarioRequest request) {
        service.atualizar(id, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/perfil")
    public ResponseEntity<UsuarioPerfilResponse> adicionarPerfil(@Valid @RequestBody UsuarioPerfilRequest request) {
        UsuarioPerfilResponse response = service.adicionarPerfil(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/perfil")
    public ResponseEntity<Void> removerPerfil(@Valid @RequestBody UsuarioPerfilRequest request) {
        service.removerPerfil(request);
        return ResponseEntity.noContent().build();
    }
}