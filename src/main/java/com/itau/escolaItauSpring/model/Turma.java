package com.itau.escolaItauSpring.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Turma {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    private String turno;
    private Integer numeroVagas;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String codigoTurma;

    @ManyToOne
    private Curso curso;
}

