package com.itau.escolaItauSpring.dto.request;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class EnderecoRequest {
    private String logradouro;
    private Long numero;
    private String cep;
    private String complemento;
    private String cidade;
    private String estado;
}
