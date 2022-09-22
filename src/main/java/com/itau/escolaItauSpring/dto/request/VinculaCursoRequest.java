package com.itau.escolaItauSpring.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VinculaCursoRequest {

    @NotNull
    private UUID professorId;

    @NotNull
    private UUID cursoDisciplinaId;

    @NotNull
    private Boolean titular;
}
