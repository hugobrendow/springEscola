package com.itau.escolaItauSpring.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CursoTurmaRequest {
    @NotNull
    private UUID id;
}
