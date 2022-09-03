package com.itau.escolaItauSpring.controller;


import com.itau.escolaItauSpring.dto.request.AlunoRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping()
    public ResponseEntity<AlunoResponse> cadastrar(
            @RequestBody @Valid AlunoRequest alunoRequest,
            UriComponentsBuilder uriComponentsBuilder
    ){
        AlunoResponse alunoResponse = alunoService.adicionar(alunoRequest);
        URI uri = uriComponentsBuilder.path("/aluno/{id}").buildAndExpand(alunoResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(alunoResponse);
    }

}
