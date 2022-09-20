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
public class DisciplinaReferencia {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "DISCIPLINA_ID")
    private Disciplina disciplina;

    @ManyToOne(optional = false)
    @JoinColumn(name = "REFERENCIA_ID")
    private Referencia referencia;
}
