package com.itau.escolaItauSpring.controller;

import com.itau.escolaItauSpring.dto.request.ProfessorRequest;
import com.itau.escolaItauSpring.dto.response.ProfessorResponse;
import com.itau.escolaItauSpring.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/professor")
@RequiredArgsConstructor
public class ProfessorController {
    private final ProfessorService professorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ProfessorResponse> cadastrar(@Valid @RequestBody ProfessorRequest professorRequest, UriComponentsBuilder uriComponentsBuilder) {
        ProfessorResponse professorResponse = professorService.adicionar(professorRequest);
        URI uri = uriComponentsBuilder.path("/professor/{id}").buildAndExpand(professorResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(professorResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponse> buscarPorId(@PathVariable UUID id) {
        ProfessorResponse professorResponse = professorService.buscarPorId(id);
        return ResponseEntity.ok(professorResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        professorService.removerProfessor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid ProfessorRequest professorRequest) {
        ProfessorResponse professorResponse = professorService.atualizar(id, professorRequest);
        return ResponseEntity.ok().body(professorResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponse>> listagem() {
        return ResponseEntity.ok(professorService.listar());
    }

    @GetMapping("/nome")
    public ResponseEntity<List<ProfessorResponse>> listagemPorNome(@RequestParam String nome) {
        List<ProfessorResponse> professorResponseList = professorService.buscarPorNome(nome);
        return ResponseEntity.ok(professorResponseList);
    }
}
