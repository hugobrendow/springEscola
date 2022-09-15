package com.itau.escolaItauSpring.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itau.escolaItauSpring.config.Telefone;
import com.itau.escolaItauSpring.enums.NivelProfessorEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class ProfessorRequest {
    @NotBlank
    @Min(value = 3, message = "o nome deve ter no mínimo 3 caracteres")
    private String nome;
    @CPF
    private String cpf;
    @NotNull
    @Email
    private String email;
    @Telefone
    @NotBlank
    private String telefone;
    @NotNull(message = "A data de admissão não pode ser nula")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataAdmissao;
    @NotNull(message = "O nível do professor não pode ser nulo")
    private NivelProfessorEnum nivel;
    @NotNull
    private EnderecoRequest endereco;
}
