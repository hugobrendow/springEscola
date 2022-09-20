package com.itau.escolaItauSpring.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OcorrenciaAlteracaoRequest {
    @NotBlank
    private String descricao;
}
