package com.itau.escolaItauSpring.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class AlunoRequest {
    @NotBlank
    private String nome;
    @NotNull
    private LocalDate dataNascimento;
    // TODO aplicar validação do CPF
    @CPF
    private String cpf;

    // TODO criar uma regex com anotação para validação de telefone
    @NotBlank
    private String telefone;
    @NotBlank
    @Email
    private String email;
    @NotNull
    private EnderecoRequest endereco;
}
