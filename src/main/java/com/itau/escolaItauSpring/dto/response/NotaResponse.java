package com.itau.escolaItauSpring.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class NotaResponse {
    private UUID id;
    private UUID matriculaId;
    private UUID cursoDisciplinaId;
    private Double nota;

}
