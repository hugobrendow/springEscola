package com.itau.escolaItauSpring.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itau.escolaItauSpring.config.Telefone;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class AlunoRequest {
    @NotNull(message = "O nome não pode ser vazio")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

    @JsonFormat(pattern="dd-MM-yyyy")
    @NotNull(message = "A data de nascimento não pode ser nula")
    private LocalDate dataNascimento;
    // TODO aplicar validação do CPF
    @CPF
    private String cpf;

    // TODO criar uma regex com anotação para validação de telefone
    @Telefone
    private String telefone;
    @NotNull
    @Email
    private String email;
    @NotNull
    private EnderecoRequest endereco;
}
