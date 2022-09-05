package com.itau.escolaItauSpring.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class CursoResponse {
    private UUID id;
    private String nome;
    private String descricao;
}
