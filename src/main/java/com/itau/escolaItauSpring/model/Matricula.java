package com.itau.escolaItauSpring.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Matricula {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private Long numero;

    private Boolean status;

    private Date data;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Turma turma;

}