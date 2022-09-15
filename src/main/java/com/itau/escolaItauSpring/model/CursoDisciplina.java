package com.itau.escolaItauSpring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CursoDisciplina {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "DISCIPLINA_ID")
    private Disciplina disciplina;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CURSO_ID")
    private Curso curso;
}
