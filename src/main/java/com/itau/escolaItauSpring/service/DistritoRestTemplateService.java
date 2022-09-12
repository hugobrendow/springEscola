package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.response.DistritoResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DistritoRestTemplateService {

    private RestTemplate restTemplate;
    private final static String URL = "https://servicodados.ibge.gov.br/api/v1/localidades/distritos";

    public DistritoRestTemplateService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public DistritoResponse[] getDistritos() {
        return restTemplate.getForObject(URL, DistritoResponse[].class);
    }
}
