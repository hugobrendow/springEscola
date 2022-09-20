package com.itau.escolaItauSpring.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
public class OcorrenciaRequest {
    @NotBlank
    private String descricao;
    @NotBlank
    private UUID alunoId;
}
