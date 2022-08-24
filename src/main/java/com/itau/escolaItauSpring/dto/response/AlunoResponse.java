package com.itau.escolaItauSpring.dto.response;

import com.itau.escolaItauSpring.model.Aluno;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter @Setter
public class AlunoResponse {
    private UUID id;
    private String nome;
    private Integer idade;
    private Long cpf;

    public AlunoResponse(Aluno aluno){
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.idade = aluno.getIdade();
        this.cpf = aluno.getCpf();
    }

    public static List<AlunoResponse> toResponse(List<Aluno> alunos){
        return alunos.stream().map(AlunoResponse::new).collect(Collectors.toList());
    }

}
