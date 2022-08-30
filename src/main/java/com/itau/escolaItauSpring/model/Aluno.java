package com.itau.escolaItauSpring.model;


import com.itau.escolaItauSpring.dto.request.AlunoRequest;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Aluno {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(name = "NOME", nullable = false, length = 200, unique = false)
    private String nome;
    @Column(name = "IDADE")
    private Integer idade;
    @Column(name = "CPF", nullable = false, unique = true)
    private Long cpf;
    @Column(name = "IS_ATIVO")
    private Boolean ativado;
//    public  Integer[] notas;
//    private Curso curso;
    public Aluno(){}

    public Aluno(AlunoRequest alunoRequest){
        this.nome = alunoRequest.getNome();
        this.idade = alunoRequest.getIdade();
        this.cpf = alunoRequest.getCpf();
        this.ativado = true;
    }
    public void exibirNotas(){
//        for(int i = 0; i < this.notas.length; i++){
//            System.out.println(notas[i]);
//        }
    }
}

