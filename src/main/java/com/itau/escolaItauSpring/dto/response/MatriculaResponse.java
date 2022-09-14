package com.itau.escolaItauSpring.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class MatriculaResponse {

    private UUID id;

    private Long numero;

    private Boolean status;

    private LocalDate data;
}
