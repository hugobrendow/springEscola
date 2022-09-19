package com.itau.escolaItauSpring.controller;

import com.itau.escolaItauSpring.dto.exception.CustomException;
import com.itau.escolaItauSpring.dto.request.MatriculaRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.dto.response.MatriculaResponse;
import com.itau.escolaItauSpring.service.MatriculaService;
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
@RequestMapping("/matricula")
@RequiredArgsConstructor
public class MatriculaController {
    
    private final MatriculaService matriculaService;

    @ApiOperation(value = "Matricular aluno")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Matrícula efetuada com sucesso", response = MatriculaResponse.class),
            @ApiResponse(code = 400, message = "Não há mais vagas disponíveis nesta turma"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<MatriculaResponse> cadastrar(@Valid @RequestBody MatriculaRequest matriculaRequest, UriComponentsBuilder uriComponentsBuilder) {
        MatriculaResponse matriculaResponse = matriculaService.matricular(matriculaRequest);
        URI uri = uriComponentsBuilder.path("/Matricula/{id}").buildAndExpand(matriculaResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(matriculaResponse);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/turma/{idTurma}")
    public ResponseEntity<List<MatriculaResponse>> listarPorTurma(@PathVariable UUID idTurma) {
        List<MatriculaResponse> matriculaResponse = matriculaService.listarPorTurma(idTurma);
        return ResponseEntity.ok(matriculaResponse);
    }
}