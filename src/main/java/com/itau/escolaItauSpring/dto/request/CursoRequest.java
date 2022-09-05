package com.itau.escolaItauSpring.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CursoRequest {
    @NotBlank(message = "O nome deve ser informado")
    private String nome;
    private String descricao;
}
