package com.itau.escolaItauSpring.controller;

import com.itau.escolaItauSpring.dto.request.PerfilRequest;
import com.itau.escolaItauSpring.dto.response.PerfilResponse;
import com.itau.escolaItauSpring.service.PerfilService;
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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/perfil")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService service;

    @PostMapping
    public ResponseEntity<PerfilResponse> criar(@Valid @RequestBody PerfilRequest request, UriComponentsBuilder uriComponentsBuilder) {
        PerfilResponse response = service.criar(request);
        URI uri = uriComponentsBuilder.path("/perfil/{id}").buildAndExpand((response.getId())).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<PerfilResponse>> listar(@PageableDefault(size = 5, page = 0, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PerfilResponse> perfis = service.listar(pageable);
        return ResponseEntity.ok(perfis);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PerfilResponse>> buscarPorUsuarioId(@PathVariable("usuarioId") UUID id) {
        List<PerfilResponse> perfis = service.buscarPorUsuarioId(id);
        return ResponseEntity.ok(perfis);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
