package com.itau.escolaItauSpring.controller;

import com.itau.escolaItauSpring.dto.exception.CustomException;
import com.itau.escolaItauSpring.dto.request.OcorrenciaAlteracaoRequest;
import com.itau.escolaItauSpring.dto.request.OcorrenciaRequest;
import com.itau.escolaItauSpring.dto.response.OcorrenciaResponse;
import com.itau.escolaItauSpring.exception.OcorrenciaNaoEncontradaException;
import com.itau.escolaItauSpring.service.OcorrenciaService;
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
@RequestMapping("/ocorrencia")
@RequiredArgsConstructor
public class OcorrenciaController {

    private final OcorrenciaService ocorrenciaService;

    @ApiOperation(value = "Listar todas as ocorrências", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listagem encontrada com sucesso", response = OcorrenciaResponse.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Usuário não possuí permissão este recurso")
    })
    @GetMapping()
    public ResponseEntity<List<OcorrenciaResponse>> buscarOcorrencias() {
        return ResponseEntity.ok(ocorrenciaService.buscarOcorrencias());
    }

    @ApiOperation(value = "Buscar ocorrência por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ocorrência encontrada com sucesso", response = OcorrenciaResponse.class),
            @ApiResponse(code = 404, message = "Ocorrência não encontrada", response = CustomException.class),
            @ApiResponse(code = 401, message = "Usuário não possuí permissão para este recurso")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaResponse> buscarOcorrencia(@PathVariable("id") UUID id) throws OcorrenciaNaoEncontradaException {
        return ResponseEntity.ok(ocorrenciaService.buscarOcorrencia(id));
    }

    @ApiOperation(value = "Cadastrar nova ocorrencia", response = OcorrenciaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Ocorrencia cadastrada com sucesso", response = OcorrenciaResponse.class),
            @ApiResponse(code = 400, message = "Informações inválidas, verifique e tente novamente", response = CustomException.class),
            @ApiResponse(code = 401, message = "Usuário não possuí permissão para este método"),
            @ApiResponse(code = 500, message = "Erro interno", response = CustomException.class)
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<OcorrenciaResponse> registrarOcorrencia(@RequestBody @Valid OcorrenciaRequest request, UriComponentsBuilder uriComponentsBuilder) {
        OcorrenciaResponse ocorrenciaResponse = ocorrenciaService.registrarOcorrencia(request);
        URI uri = uriComponentsBuilder.path("/ocorrencia/{id}").buildAndExpand(ocorrenciaResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(ocorrenciaResponse);
    }

    @ApiOperation(value = "Editar ocorrencia", response = OcorrenciaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ocorrencia editada com sucesso", response = OcorrenciaResponse.class),
            @ApiResponse(code = 400, message = "Informações inválidas, verifique e tente novamente", response = CustomException.class),
            @ApiResponse(code = 401, message = "Usuário não possuí permissão para este método"),
            @ApiResponse(code = 500, message = "Erro interno", response = CustomException.class)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<OcorrenciaResponse> editarOcorrencia(@PathVariable("id") UUID id, @RequestBody @Valid OcorrenciaAlteracaoRequest request) {
        return ResponseEntity.ok(ocorrenciaService.alterarOcorrencia(id, request));
    }

    @ApiOperation(value = "Deletar ocorrência por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Ocorrência deletada com sucesso", response = OcorrenciaResponse.class),
            @ApiResponse(code = 404, message = "Ocorrência não encontrada", response = CustomException.class)
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarOcorrencia(@PathVariable UUID id) {
        ocorrenciaService.deletarOcorrencia(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity<List<OcorrenciaResponse>> listarOcorrenciasPorAluno(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(ocorrenciaService.listarOcorrenciasPorAluno(id));
    }

}
