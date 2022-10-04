package com.itau.escolaItauSpring.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CursoDisciplinaResponse {

    private UUID id;
    private DisciplinaResponse disciplinaResponse;
    private CursoResponse cursoResponse;

}
