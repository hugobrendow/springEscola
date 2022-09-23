package com.itau.escolaItauSpring.controller;

import com.itau.escolaItauSpring.dto.exception.CustomException;
import com.itau.escolaItauSpring.dto.request.ProfessorRequest;
import com.itau.escolaItauSpring.dto.response.ProfessorResponse;
import com.itau.escolaItauSpring.enums.NivelProfessorEnum;
import com.itau.escolaItauSpring.service.ProfessorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @ApiOperation(value = "Cadastrar novo professor")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Professor cadastrado com sucesso", response = ProfessorResponse.class),
        @ApiResponse(code = 400, message = "Informações inválidas, verifique e tente novamente", response = CustomException.class),
        @ApiResponse(code = 401, message = "Usuário não possui permissão para este método"),
        @ApiResponse(code = 500, message = "Erro interno", response = CustomException.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Secured({"ROLE_COORDENADOR", "ROLE_SECRETARIO"})
    public ResponseEntity<ProfessorResponse> cadastrar(@Valid @RequestBody ProfessorRequest professorRequest,
                                                       UriComponentsBuilder uriComponentsBuilder) {
        ProfessorResponse professorResponse = professorService.adicionar(professorRequest);
        URI uri = uriComponentsBuilder.path("/professor/{id}").buildAndExpand(professorResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(professorResponse);
    }
    @ApiOperation(value = "Buscar professor por ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Professor encontrado com sucesso", response = ProfessorResponse.class),
        @ApiResponse(code = 404, message = "Professor não encontrado", response = CustomException.class),
        @ApiResponse(code = 401, message = "Usuário não possuí permissão para este recurso"), })
    @GetMapping("/{id}")
    @Secured({"ROLE_COORDENADOR", "ROLE_SECRETARIO", "ROLE_PROFESSOR"})
    public ResponseEntity<ProfessorResponse> buscarPorId(@PathVariable UUID id) {
        ProfessorResponse professorResponse = professorService.buscarPorId(id);
        return ResponseEntity.ok(professorResponse);
    }

    @ApiOperation(value = "Remover professor")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Professor removido com sucesso", response = ProfessorResponse.class),
        @ApiResponse(code = 400, message = "Professor não encontrado", response = CustomException.class),
        @ApiResponse(code = 401, message = "Usuário não possuí permissão para este método"),
        @ApiResponse(code = 500, message = "Erro interno", response = CustomException.class) })
    @DeleteMapping("/{id}")
    @Secured({"ROLE_COORDENADOR", "ROLE_SECRETARIO"})
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        professorService.removerProfessor(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Atualizar professor")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Professor atualizado com sucesso", response = ProfessorResponse.class),
        @ApiResponse(code = 401, message = "Usuário não possui permissão para este recurso"),
        @ApiResponse(code = 404, message = "Professor não encontrado", response = CustomException.class),
        @ApiResponse(code = 500, message = "Erro interno", response = CustomException.class) })
    @PutMapping("/{id}")
    @Secured({"ROLE_COORDENADOR", "ROLE_SECRETARIO"})
    public ResponseEntity<ProfessorResponse> atualizar(@PathVariable UUID id,
                                                       @RequestBody @Valid ProfessorRequest professorRequest) {
        ProfessorResponse professorResponse = professorService.atualizar(id, professorRequest);
        return ResponseEntity.ok().body(professorResponse);
    }

    @ApiOperation(value = "Buscar todos os professores")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Professor(es) encontrado(s) com sucesso", response = ProfessorResponse.class),
        @ApiResponse(code = 404, message = "Professor(es) não encontrado(s)", response = CustomException.class),
        @ApiResponse(code = 401, message = "Usuário não possuí permissão para este recurso"), })
    @GetMapping
    @Secured({"ROLE_COORDENADOR", "ROLE_SECRETARIO", "ROLE_PROFESSOR"})
    public ResponseEntity<List<ProfessorResponse>> listagem(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(professorService.listar(pageable));
    }

    @ApiOperation(value = "Buscar pofessor por nome, cpf ou nivel")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Professor(es) encontrado(s) com sucesso", response = ProfessorResponse.class),
        @ApiResponse(code = 404, message = "Professor(es) não encontrado(s)", response = CustomException.class),
        @ApiResponse(code = 401, message = "Usuário não possuí permissão para este recurso"), })
    @GetMapping("/filtro")
    @Secured({"ROLE_COORDENADOR", "ROLE_SECRETARIO", "ROLE_PROFESSOR"})
    public ResponseEntity<List<ProfessorResponse>> listagemFiltro(@RequestParam(required = false) String nome,
                                                                  @RequestParam(required = false) String cpf,
                                                                  @RequestParam(required = false) NivelProfessorEnum nivel,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        List<ProfessorResponse> professorResponseList = professorService.filtro(nome, cpf, nivel, pageable);
        return ResponseEntity.ok(professorResponseList);
    }
}
