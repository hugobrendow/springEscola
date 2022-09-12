package com.itau.escolaItauSpring.dto.response;

import lombok.Data;

@Data
public class CepResponse {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ddd;
}
