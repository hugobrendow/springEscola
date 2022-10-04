package com.itau.escolaItauSpring.controller;


import com.itau.escolaItauSpring.dto.request.CursoRequest;
import com.itau.escolaItauSpring.dto.response.CursoResponse;
import com.itau.escolaItauSpring.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/curso")
@RequiredArgsConstructor
@RolesAllowed({"ROLE_PROFESSOR"})
public class CursoController {
    private final CursoService service;

    @PostMapping
    @RolesAllowed({"ROLE_COORDENADOR"})
    public ResponseEntity<CursoResponse> criar(@RequestBody @Valid CursoRequest cursoRequest, UriComponentsBuilder uriComponentsBuilder) {
        CursoResponse curso = service.criar(cursoRequest);
        URI uri = uriComponentsBuilder.path("/curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(curso);
    }

    @GetMapping
//    @PreAuthorize("permitAll()")
    public ResponseEntity<Page<CursoResponse>> listar(@PageableDefault(size = 5, page = 0, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CursoResponse> cursos = service.listar(pageable);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
//    @PostAuthorize("hasRole('ROLE_COORDENADOR')")
    public ResponseEntity<CursoResponse> buscarPorId(@PathVariable UUID id) {
        CursoResponse curso = service.buscarPorId(id);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_COORDENADOR')")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtrar")
    @Secured({"ROLE_COORDENADOR", "ROLE_PROFESSOR"})
    public ResponseEntity<List<CursoResponse>> filtrar(
            @RequestParam String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "7") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        List<CursoResponse> cursos = service.filtrar(nome, pageable);
        return ResponseEntity.ok(cursos);
    }
    // TODO atualizar curso
}
