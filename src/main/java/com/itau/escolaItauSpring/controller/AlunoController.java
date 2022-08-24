package com.itau.escolaItauSpring.controller;

import com.itau.escolaItauSpring.dto.request.AlunoRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    public List<AlunoResponse> aluno(){
        return AlunoResponse.toResponse(alunoService.listar());
    }

    @PostMapping
    public AlunoResponse cadastrar(@RequestBody AlunoRequest aluno){
        return new AlunoResponse(alunoService.adicionar(aluno));
    }

}
