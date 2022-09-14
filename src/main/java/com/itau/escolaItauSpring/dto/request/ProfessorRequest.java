package com.itau.escolaItauSpring.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itau.escolaItauSpring.config.Telefone;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
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
    @JsonFormat(pattern = "dd-MM-YYYY")
    private LocalDate dataAdmissao;
    // TODO precisa de nivel?
    
}
