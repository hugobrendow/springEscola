package com.itau.escolaItauSpring.model;

import com.itau.escolaItauSpring.enums.StatusMatricula;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Matricula {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(columnDefinition="serial")
    @Generated(GenerationTime.INSERT)
    private Long numero;

    //@Column(columnDefinition = "StatusMatricula default ATIVADA")
    private StatusMatricula status = StatusMatricula.ATIVADA;

    @CreationTimestamp
    private LocalDate data;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Turma turma;

    public Matricula (Aluno aluno, Turma turma){
        this.aluno = aluno;
        this.turma = turma;
    }
}