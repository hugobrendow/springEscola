package com.itau.escolaItauSpring.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class AlunoRequest {
    @NotNull(message = "O nome não pode ser vazio")
    @Length(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "A data de nascimento não pode ser nula")
    private LocalDate dataNascimento;
    @CPF
    private String cpf;
    private String telefone;
    @NotNull
    @Email
    private String email;
    @NotNull
    private EnderecoRequest endereco;
}
