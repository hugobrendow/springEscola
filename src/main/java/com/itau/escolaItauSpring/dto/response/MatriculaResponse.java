package com.itau.escolaItauSpring.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itau.escolaItauSpring.enums.StatusMatricula;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class MatriculaResponse {

    private UUID id;

    private String codigo;

    private StatusMatricula status;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate data;

    private UUID idAluno;

    private UUID idTurma;

}