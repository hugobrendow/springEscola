package com.itau.escolaItauSpring.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class OcorrenciaRequest {
    @Length(min = 5)
    private String descricao;
    @NotNull
    private UUID alunoId;
}
