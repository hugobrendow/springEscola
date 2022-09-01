package com.itau.escolaItauSpring.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CursoRequest {
    private String nome;
    private ProfessorRequest professor;
}
