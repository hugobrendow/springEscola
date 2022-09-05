package com.itau.escolaItauSpring.controller;


import com.itau.escolaItauSpring.dto.request.AlunoRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {
    private final AlunoService alunoService;
    @PostMapping
    public ResponseEntity<AlunoResponse> cadastrar(@Valid @RequestBody AlunoRequest alunoRequest, UriComponentsBuilder uriComponentsBuilder) {
        AlunoResponse alunoResponse = alunoService.adicionar(alunoRequest);
        URI uri = uriComponentsBuilder.path("/aluno/{id}").buildAndExpand(alunoResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(alunoResponse);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listagem() {
        return ResponseEntity.ok(alunoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> buscarPorId(@PathVariable UUID id) {
        AlunoResponse alunoResponse = alunoService.buscarPorId(id);
        return ResponseEntity.ok(alunoResponse);
    }

    // TODO criar método para atualização

}
