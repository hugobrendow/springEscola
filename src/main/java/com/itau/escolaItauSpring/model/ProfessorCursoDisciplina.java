package com.itau.escolaItauSpring.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorCursoDisciplina {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PROFESSOR_ID")
    private Professor professor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CURSO_DISCIPLINA_ID")
    private CursoDisciplina cursoDisciplina;

    private Boolean titular;
}
