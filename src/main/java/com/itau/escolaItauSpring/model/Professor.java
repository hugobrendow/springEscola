package com.itau.escolaItauSpring.model;

import com.itau.escolaItauSpring.enums.NivelProfessorEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Professor {
    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(nullable = false, length = 200)
    private String nome;
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String telefone;
    private LocalDate dataAdmissao;
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
    private NivelProfessorEnum nivel;
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;
}
