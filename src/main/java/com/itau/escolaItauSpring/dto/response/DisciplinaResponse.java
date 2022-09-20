package com.itau.escolaItauSpring.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DisciplinaResponse {

    private UUID id;
    private String nome;
    private Integer cargaHoraria;

}
