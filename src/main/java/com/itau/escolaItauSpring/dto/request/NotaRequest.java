package com.itau.escolaItauSpring.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class NotaRequest {
    @NotNull(message = "O id da matricula não pode ser nula")
    private UUID matriculaId;
    @NotNull(message = "O id do curso disciplina não pode ser nulo")
    private UUID cursoDisciplinaId;
    private Double nota;
}
