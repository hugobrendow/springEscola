package com.itau.escolaItauSpring.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class Nota {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    private BigDecimal nota;

    @JoinColumn(name = "matricula_id")
    @ManyToOne
    private Matricula matricula;

    @JoinColumn(name = "curso_disciplina_id")
    @ManyToOne
    private CursoDisciplina cursoDisciplina;

    public Nota() {}
    public Nota(BigDecimal nota, Matricula matricula, CursoDisciplina cursoDisciplina) {
        this.nota = nota;
        this.matricula = matricula;
        this.cursoDisciplina = cursoDisciplina;
    }
}



