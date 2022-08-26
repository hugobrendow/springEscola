package com.itau.escolaItauSpring.model;


import com.itau.escolaItauSpring.dto.request.AlunoRequest;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Aluno {

    private UUID id;
    private String nome;
    private Integer idade;
    private Long cpf;
    private Boolean ativado;
    public  Integer[] notas;
    private Curso curso;
    public Aluno(){}

    public Aluno(AlunoRequest alunoRequest){
        this.nome = alunoRequest.getNome();
        this.idade = alunoRequest.getIdade();
        this.cpf = alunoRequest.getCpf();
        this.ativado = true;
    }
    public void exibirNotas(){
        for(int i = 0; i < this.notas.length; i++){
            System.out.println(notas[i]);
        }
    }
}

