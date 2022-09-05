package com.itau.escolaItauSpring.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Endereco {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    private String logradouro;
    private Long numero;
    private String cep;
    private String complemento;
    private String cidade;
    private String estado;
}
