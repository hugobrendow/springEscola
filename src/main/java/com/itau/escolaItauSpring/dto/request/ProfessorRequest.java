package com.itau.escolaItauSpring.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itau.escolaItauSpring.config.Telefone;
import com.itau.escolaItauSpring.enums.NivelProfessorEnum;
import com.itau.escolaItauSpring.model.Usuario;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorRequest {
    @NotBlank
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
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
    @NotNull(message = "O endereço não pode ser nulo")
    private EnderecoRequest endereco;
    @NotNull(message = "O nível do professor não pode ser nulo")
    private NivelProfessorEnum nivel;
    @NotNull(message = "O usuário do professor não pode ser nulo")
    private Usuario usuario;
}
