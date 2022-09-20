package com.itau.escolaItauSpring.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class MatriculaRequest {

    @NotNull(message = "É necessário ter um aluno para a realização da matrícula")
    private UUID idAluno;

    @NotNull(message = "É necessário ter uma turma para a realização da matrícula")
    private UUID idTurma;

}
