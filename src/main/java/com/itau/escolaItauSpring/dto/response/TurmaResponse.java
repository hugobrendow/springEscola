package com.itau.escolaItauSpring.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class TurmaResponse {
    private UUID id;
    private String turno;
    private Integer numeroVagas;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String codigoTurma;
    private CursoResponse curso;
}
