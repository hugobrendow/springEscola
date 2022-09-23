package com.itau.escolaItauSpring.dto.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class NotaRequest {
    @NotNull(message = "O id da matricula é obrigatório")
    private UUID matriculaId;
    @NotNull(message = "O id do curso disciplina é obrigatório")
    private UUID cursoDisciplinaId;

    @Min(0)
    @Max(10)
    private BigDecimal nota;
}
