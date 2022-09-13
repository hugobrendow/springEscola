package com.itau.escolaItauSpring.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Audited
public class Aluno {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    // TODO mostrar na próxima aula como é criado
    @Column(nullable = false, length = 200)
    private String nome;
    private LocalDate dataNascimento;
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    private String telefone;
    @Column(nullable = false, unique = true)
    private String email;
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ENDERECO_ID")
    private Endereco endereco;

}

