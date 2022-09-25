package com.itau.escolaItauSpring.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthRequest {
    @NotBlank
    @Size(min = 3)
    private String username;
    @NotBlank
    @Size(min = 3)
    private String password;
}
