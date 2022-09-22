package com.itau.escolaItauSpring.controller;


import com.itau.escolaItauSpring.dto.exception.CustomException;
import com.itau.escolaItauSpring.dto.request.NotaRequest;
import com.itau.escolaItauSpring.dto.request.NotaUpdateRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.dto.response.NotaResponse;
import com.itau.escolaItauSpring.service.NotaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/nota")
@RequiredArgsConstructor
@RolesAllowed({"ROLE_COORDENADOR", "ROLE_PROFESSOR", "ROLE_ALUNO", "ROLE_SECRETARIA"})
public class NotaController {

    private final NotaService notaService;

    @ApiOperation(value = "Cadastrar novo nota")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Nota cadastrada com sucesso", response = NotaResponse.class),
            @ApiResponse(code = 400, message = "Informações inválidas, verifique e tente novamente", response = CustomException.class),
            @ApiResponse(code = 401, message = "Usuário não possuí permissão este recurso"),
            @ApiResponse(code = 500, message = "Erro interno", response = CustomException.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @RolesAllowed({"ROLE_COORDENADOR", "ROLE_PROFESSOR"})
    public ResponseEntity<NotaResponse> cadastrar(@RequestBody @Valid NotaRequest notaRequest, UriComponentsBuilder uriComponentsBuilder) {
        NotaResponse notaResponse = notaService.adicionar(notaRequest);
        URI uri = uriComponentsBuilder.path("/nota/{id}").buildAndExpand(notaResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(notaResponse);
    }


    @ApiOperation(value = "Listar notas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Notas encontradas com sucesso", response = CustomException.class),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 403, message = "É necessario ter permissão Coordenador ,Professor ou Secretaria"),
    })
    @GetMapping
    @RolesAllowed({"ROLE_COORDENADOR", "ROLE_PROFESSOR", "ROLE_SECRETARIA"})
    public ResponseEntity<List<NotaResponse>> listar() {
        return ResponseEntity.ok(notaService.listar());
    }

    @ApiOperation(value = "Lista notas por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Nota encontrada com sucesso", response = CustomException.class),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 404, message = "Nota não encontrada"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<NotaResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(notaService.buscarPorId(id));
    }

    @ApiOperation(value = "Atualiza nota")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Nota atualizada com sucesso", response = CustomException.class),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 403, message = "É necessario ter permissão Coordenador ou Professor"),
    })
    @PatchMapping("/{id}")
    @RolesAllowed({"ROLE_COORDENADOR", "ROLE_PROFESSOR"})
    public ResponseEntity<NotaResponse> atualizar(@PathVariable UUID id,
                                                  @RequestBody @Valid NotaUpdateRequest notaUpdateRequest) {
        return ResponseEntity.ok(notaService.atualizar(id, notaUpdateRequest));
    }

    @ApiOperation(value = "Remove nota")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Nota excluida com sucesso", response = CustomException.class),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 403, message = "É necessario ter permissão Coordenador ou Professor"),
    })
    @DeleteMapping("/{id}")
    @RolesAllowed({"ROLE_COORDENADOR", "ROLE_PROFESSOR"})
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        notaService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Lista notas por Matricula")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Nota encontrada com sucesso", response = CustomException.class),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 403, message = "É necessario ter permissão Coordenador ou Professor"),
            @ApiResponse(code = 404, message = "Nota não encontrada"),
    })
    @GetMapping("/matricula/{id}")
    //@RolesAllowed("ROLE_ALUNO")
    public ResponseEntity<List<NotaResponse>> listarPorMatricula(@PathVariable UUID id) {

        return ResponseEntity.ok(notaService.listarPorMatricula(id));
    }

    @ApiOperation(value = "Lista notas por CursoDisciplina")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Nota encontrada com sucesso", response = CustomException.class),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 403, message = "É necessario ter permissão Coordenador ou Professor"),
            @ApiResponse(code = 404, message = "Nota não encontrada"),
    })
    @GetMapping("/curso-disciplina/{id}")
    public ResponseEntity<List<NotaResponse>> listarPorCursoDisciplina(@PathVariable UUID id) {
        return ResponseEntity.ok(notaService.listarPorCursoDisciplina(id));
    }
}
