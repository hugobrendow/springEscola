package com.itau.escolaItauSpring.controller;

import com.itau.escolaItauSpring.dto.request.TurmaRequest;
import com.itau.escolaItauSpring.dto.response.TurmaResponse;
import com.itau.escolaItauSpring.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/turma")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService turmaService;

    @PostMapping
    public ResponseEntity<TurmaResponse> criar(@RequestBody @Valid TurmaRequest turmaRequest) {
        return ResponseEntity.ok(turmaService.criar(turmaRequest));
    }
}
