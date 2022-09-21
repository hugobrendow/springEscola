package com.itau.escolaItauSpring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequest {
    private String logradouro;
    private Long numero;
    private String cep;
    private String complemento;
    private String cidade;
    private String estado;
}
