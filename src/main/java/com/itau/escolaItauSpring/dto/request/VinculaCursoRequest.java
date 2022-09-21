package com.itau.escolaItauSpring.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Builder
public class VinculaCursoRequest {

    @NotNull
    private UUID professorId;

    @NotNull
    private UUID cursoDisciplinaId;

    @NotNull
    private Boolean titular;
}
