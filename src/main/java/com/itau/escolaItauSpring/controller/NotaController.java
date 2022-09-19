package com.itau.escolaItauSpring.controller;


import com.itau.escolaItauSpring.dto.request.NotaRequest;
import com.itau.escolaItauSpring.dto.response.NotaResponse;
import com.itau.escolaItauSpring.service.NotaService;
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
@RequestMapping("/nota")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService notaService;

    //    @ApiOperation(value = "Cadastrar novo nota")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Nota cadastrada com sucesso", response = AlunoResponse.class),
//            @ApiResponse(code = 400, message = "Informações inválidas, verifique e tente novamente", response = CustomException.class),
//            @ApiResponse(code = 401, message = "Nota não possuí permissão para este método"),
//            @ApiResponse(code = 500, message = "Erro interno", response = CustomException.class)
//    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<NotaResponse> cadastrar(@RequestBody @Valid NotaRequest notaRequest, UriComponentsBuilder uriComponentsBuilder) {
        NotaResponse notaResponse = notaService.adicionar(notaRequest);
        URI uri = uriComponentsBuilder.path("/nota/{id}").buildAndExpand(notaResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(notaResponse);
    }

    @GetMapping
    public ResponseEntity<List<NotaResponse>> listar() {
        return ResponseEntity.ok(notaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(notaService.buscarPorId(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NotaResponse> atualizar(@PathVariable UUID id,
              @RequestBody NotaRequest notaRequest) {
        return ResponseEntity.ok(notaService.atualizar(id, notaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        notaService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/matricula/{id}")
    public ResponseEntity<List<NotaResponse>> listarPorMatricula(@PathVariable UUID id) {
        return ResponseEntity.ok(notaService.listarPorMatricula(id));
    }

    @GetMapping("/curso-disciplina/{id}")
    public ResponseEntity<List<NotaResponse>> listarPorCursoDisciplina(@PathVariable UUID id) {
        return ResponseEntity.ok(notaService.listarPorCursoDisciplina(id));
    }
}