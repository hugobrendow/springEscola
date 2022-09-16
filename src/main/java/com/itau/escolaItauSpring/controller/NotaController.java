package com.itau.escolaItauSpring.controller;


import com.itau.escolaItauSpring.dto.exception.CustomException;
import com.itau.escolaItauSpring.dto.request.NotaRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.dto.response.NotaResponse;
import com.itau.escolaItauSpring.service.NotaService;
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
//    @PostMapping
//    public String testeNota(){
//        return "Ta funcionando";
//    }
}
