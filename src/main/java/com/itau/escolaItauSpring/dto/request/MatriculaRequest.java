package com.itau.escolaItauSpring.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class MatriculaRequest {

    @NotNull
    private UUID idAluno;

    @NotNull
    private UUID idTurma;

}
