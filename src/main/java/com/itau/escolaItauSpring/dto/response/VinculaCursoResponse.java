package com.itau.escolaItauSpring.dto.response;

import com.itau.escolaItauSpring.model.CursoDisciplina;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class VinculaCursoResponse {
    private UUID id;
    private ProfessorResponse professor;
    private CursoDisciplinaResponse cursoDisciplina;
    private Boolean titular;
}
