package com.itau.escolaItauSpring.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class TurmaRequest {
    private String turno;
    private Integer numeroVagas;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataInicio;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataFim;
    private String codigoTurma;
    @NotNull
    private CursoTurmaRequest curso;
}
