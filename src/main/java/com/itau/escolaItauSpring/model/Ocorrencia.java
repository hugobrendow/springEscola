package com.itau.escolaItauSpring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
public class Ocorrencia {
    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private String descricao;
    //    @CreatedDate
    @CreationTimestamp
    private LocalDateTime dataHora;
    @UpdateTimestamp
    private LocalDateTime dataHoraAlteracao;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
}
