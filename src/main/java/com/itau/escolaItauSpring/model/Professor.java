package com.itau.escolaItauSpring.model;

import com.itau.escolaItauSpring.enums.NivelProfessorEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Professor {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(nullable = false, length = 200)
    private String nome;
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String telefone;
    private LocalDate dataAdmissao;
    private NivelProfessorEnum nivel;
    @Column(nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
    //TODO PROFESSOR_CURSO_DISCIPLINA
}
