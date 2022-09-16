package com.itau.escolaItauSpring.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class MatriculaResponse {

    private UUID id;

    private Long numero;

    private Boolean status;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate data;

    private UUID idAluno;

    private UUID idTurma;

}