package com.itau.escolaItauSpring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Professor {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    private String nome;
    private String cpf;
    private LocalDate dataAdmissao;
    private String telefone;
    @Column(nullable = false, unique = true)
    private String email;

    //TODO Confirmar tipo "nivel"
}
