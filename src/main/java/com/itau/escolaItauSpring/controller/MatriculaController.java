package com.itau.escolaItauSpring.controller;

import com.itau.escolaItauSpring.dto.request.MatriculaRequest;
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
            @ApiResponse(code = 201, message = "Matrícula efetuada com sucesso!", response = MatriculaResponse.class),
            @ApiResponse(code = 400, message = "Não há mais vagas disponíveis nesta turma!"),
            @ApiResponse(code = 409, message = "Aluno já matriculado nesta turma!")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<MatriculaResponse> matricular(@Valid @RequestBody MatriculaRequest matriculaRequest, UriComponentsBuilder uriComponentsBuilder) {
        MatriculaResponse matriculaResponse = matriculaService.matricular(matriculaRequest);
        URI uri = uriComponentsBuilder.path("/Matricula/{id}").buildAndExpand(matriculaResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(matriculaResponse);
    }

    @ApiOperation(value = "Buscar Matricula")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Matrícula encontrada!", response = MatriculaResponse.class),
            @ApiResponse(code = 404, message = "Matrícula não existente!"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<MatriculaResponse> buscarMatricula(@PathVariable UUID id) {
        MatriculaResponse matriculaResponse = matriculaService.buscarPorId(id);
        return ResponseEntity.ok(matriculaResponse);
    }

    @ApiOperation(value = "Buscar Matriculas por Turma")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Matrículas encontradas!",
                    response = MatriculaResponse.class,
                    responseContainer = "List"),
            @ApiResponse(code = 404, message = "Turma não existente!"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/turma/{idTurma}")
    public ResponseEntity<List<MatriculaResponse>> listarPorTurma(@PathVariable UUID idTurma) {
        List<MatriculaResponse> matriculaResponse = matriculaService.listarPorTurma(idTurma);
        return ResponseEntity.ok(matriculaResponse);
    }

    @ApiOperation(value = "Buscar Matriculas por Aluno")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Matrículas encontradas!",
                    response = MatriculaResponse.class,
                    responseContainer = "List"),
            @ApiResponse(code = 404, message = "Aluno não existente!"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/aluno/{idAluno}")
    public ResponseEntity<List<MatriculaResponse>> listarPorAluno(@PathVariable UUID idAluno) {
        List<MatriculaResponse> matriculaResponse = matriculaService.listarPorAluno(idAluno);
        return ResponseEntity.ok(matriculaResponse);
    }

    @ApiOperation(value = "Trancar Matricula")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Matrícula trancada!", response = MatriculaResponse.class),
            @ApiResponse(code = 404, message = "Matrícula não existente!"),
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/trancar/{id}")
    public ResponseEntity<MatriculaResponse> trancarMatricula(@PathVariable UUID id) {
        MatriculaResponse matriculaResponse = matriculaService.trancarMatricula(id);
        return ResponseEntity.ok(matriculaResponse);
    }

    @ApiOperation(value = "Ativar Matricula")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Matrícula ativada!", response = MatriculaResponse.class),
            @ApiResponse(code = 404, message = "Matrícula não existente!"),
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/ativar/{id}")
    public ResponseEntity<MatriculaResponse> ativarMatricula(@PathVariable UUID id) {
        MatriculaResponse matriculaResponse = matriculaService.ativarMatricula(id);
        return ResponseEntity.ok(matriculaResponse);
    }

    @ApiOperation(value = "Cancelar Matrícula")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Matrícula cancelada!", response = MatriculaResponse.class),
            @ApiResponse(code = 404, message = "Matrícula não existente!"),
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/cancelar/{id}")
    public ResponseEntity<MatriculaResponse> cancelarMatricula(@PathVariable UUID id) {
        MatriculaResponse matriculaResponse = matriculaService.cancelarMatricula(id);
        return ResponseEntity.ok(matriculaResponse);
    }
}