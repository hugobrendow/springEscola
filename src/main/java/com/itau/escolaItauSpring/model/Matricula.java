package com.itau.escolaItauSpring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numero;

    @ColumnDefault("true")
    private Boolean status;

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