package com.itau.escolaItauSpring.controller;

import com.itau.escolaItauSpring.dto.response.CepResponse;
import com.itau.escolaItauSpring.service.CepFeignClient;
import com.itau.escolaItauSpring.service.CepRestTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep")
@RequiredArgsConstructor
public class CepController {

    private final CepRestTemplateService cepRestTemplateService;
    private final CepFeignClient cepFeignClient;

    @GetMapping("/{cep}")
    public ResponseEntity<CepResponse> listar(@PathVariable("cep") String cep) {
        CepResponse cepResponse = cepRestTemplateService.getCep(cep);
        return ResponseEntity.ok(cepResponse);
    }

    @GetMapping("/{cep}/feign")
    public ResponseEntity<CepResponse> listarFeign(@PathVariable("cep") String cep) {
        CepResponse resultado = cepFeignClient.getCep(cep);
        return ResponseEntity.ok(resultado);
    }
}
