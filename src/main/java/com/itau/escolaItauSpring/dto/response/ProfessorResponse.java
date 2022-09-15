package com.itau.escolaItauSpring.dto.response;

import com.itau.escolaItauSpring.enums.NivelProfessorEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProfessorResponse {
    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private NivelProfessorEnum nivel;
}
