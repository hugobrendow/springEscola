package com.itau.escolaItauSpring.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UsuarioPerfilResponse {
    private UUID id;
    private UsuarioResponse usuario;
    private PerfilResponse perfil;
}
