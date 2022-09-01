package com.itau.escolaItauSpring.model;


import com.itau.escolaItauSpring.dto.request.AlunoRequest;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ENDERECO_ID")
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ALUNO_ID")
    private List<Nota> notas;

//    public  Integer[] notas;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ALUNO_ID")
    private List<Curso> cursos;
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

