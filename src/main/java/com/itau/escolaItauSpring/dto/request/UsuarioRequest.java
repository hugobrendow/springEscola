package com.itau.escolaItauSpring.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UsuarioRequest {
    @NotBlank
    @Size(min = 3, message = "O username deve conter no mínimo 3 caracteres.")
    private String username;

    @NotBlank
    @Size(min = 3, message = "o password deve conter no mínimo 3 caracteres.")
    private String password;
    private Boolean accountNonExpired = true;
    private Boolean accountNonLocked = true;
    private Boolean credentialsNonExpired = true;
    private Boolean enabled = true;
}
