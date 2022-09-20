package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.response.CepResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepRestTemplateService {
    private final RestTemplate restTemplate;
    private static final String URL = "http://viacep.com.br/ws/";

    public CepRestTemplateService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CepResponse getCep(String cep) {
        String urlCompleta = URL + cep + "/json";
        return restTemplate.getForObject(urlCompleta, CepResponse.class);
    }

}
