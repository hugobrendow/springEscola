package com.itau.escolaItauSpring.controller;

import com.itau.escolaItauSpring.dto.response.DistritoResponse;
import com.itau.escolaItauSpring.service.DistritoRestTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distrito")
@RequiredArgsConstructor
public class DistritoController {
    private final DistritoRestTemplateService restTemplateService;

    @GetMapping
    public ResponseEntity<DistritoResponse[]> listar() {
        return ResponseEntity.ok(restTemplateService.getDistritos());
    }
}
