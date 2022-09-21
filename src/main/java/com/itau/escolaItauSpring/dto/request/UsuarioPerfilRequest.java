package com.itau.escolaItauSpring.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UsuarioPerfilRequest {
    @NotNull
    private UUID usuarioId;
    @NotNull
    private UUID perfilId;
}