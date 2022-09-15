package com.itau.escolaItauSpring.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Getter @Setter
public class AlunoResponse {
    private UUID id;
    private String nome;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
}
