package com.itau.escolaItauSpring.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Getter @Setter
public class Curso {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    private Professor professor;
    // Turno -> ENUM
    // Professor
    // Carga Horária
    // Data término
    // Ementa -> OUTRO OBJETO
}
