package com.itau.escolaItauSpring.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class OcorrenciaAlteracaoRequest {
    @Length(min = 5)
    private String descricao;
}
