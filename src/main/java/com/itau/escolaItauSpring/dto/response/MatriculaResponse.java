package com.itau.escolaItauSpring.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class MatriculaResponse {

    private UUID id;

    private Long numero;

    private Boolean status;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate data;

    private DadosAluno aluno;

    private DadosTurma turma;

    //podemos criar um dto ou tirar de dentro de um objeto a parte
    //adaptar o json para ficar explícito a qual id se refere
    public class DadosAluno {
        private UUID id;

        private String nome;
    }

    //devolver id da turma e nome do curso
    public class DadosTurma {
        private UUID id;
        private String turno;
        @JsonFormat(pattern="dd-MM-yyyy")
        private LocalDate dataInicio;
        @JsonFormat(pattern="dd-MM-yyyy")
        private LocalDate dataFim;
        private String codigoTurma;
        private CursoResponse curso;
    }
}