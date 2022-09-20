package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.response.CepResponse;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CepWebClientService {
    private WebClient webClient;

    public CepWebClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://viacep.com.br/ws/")
                .build();
    }

    public CepResponse getCep(String cep) {
        CepResponse cepResponse = this.webClient
                .get()
                .uri("/{cep}/json/", cep)
                .retrieve()
                .toEntity(CepResponse.class)
                .blockOptional()
                .orElseThrow(() -> new ItemNaoExistenteException())
                .getBody();
        return cepResponse;
    }
}
