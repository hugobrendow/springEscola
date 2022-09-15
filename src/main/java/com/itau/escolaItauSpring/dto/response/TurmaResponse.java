package com.itau.escolaItauSpring.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class TurmaResponse {
    private UUID id;
    private String turno;
    private Integer numeroVagas;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataInicio;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataFim;
    private String codigoTurma;
    private CursoResponse curso;


}
