package com.itau.escolaItauSpring.controller;


import com.itau.escolaItauSpring.dto.exception.CustomException;
import com.itau.escolaItauSpring.dto.request.AlunoRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.service.AlunoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {
    private final AlunoService alunoService;

    @ApiOperation(value = "Cadastrar novo aluno")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Aluno cadastrado com sucesso", response = AlunoResponse.class),
            @ApiResponse(code = 400, message = "Informações inválidas, verifique e tente novamente", response = CustomException.class),
            @ApiResponse(code = 401, message = "Usuário não possuí permissão para este método"),
            @ApiResponse(code = 500, message = "Erro interno", response = CustomException.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<AlunoResponse> cadastrar(@Valid @RequestBody AlunoRequest alunoRequest, UriComponentsBuilder uriComponentsBuilder) {
        AlunoResponse alunoResponse = alunoService.adicionar(alunoRequest);
        URI uri = uriComponentsBuilder.path("/aluno/{id}").buildAndExpand(alunoResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(alunoResponse);
    }

    @ApiOperation(value = "Listar todos os alunos", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listagem encontrada com sucesso", response = AlunoResponse.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Usuário não possuí permissão este recurso", response = AlunoResponse.class)
    })
    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listagem() {
        return ResponseEntity.ok(alunoService.listar());
    }

    @ApiOperation(value = "Buscar aluno por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Aluno encontrado com sucesso", response = AlunoResponse.class),
            @ApiResponse(code = 404, message = "Aluno não encontrado", response = CustomException.class),
            @ApiResponse(code = 401, message = "Usuário não possuí permissão para este recurso"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> buscarPorId(@PathVariable UUID id) {
        AlunoResponse alunoResponse = alunoService.buscarPorId(id);
        return ResponseEntity.ok(alunoResponse);
    }

    // TODO criar método para atualização

}
