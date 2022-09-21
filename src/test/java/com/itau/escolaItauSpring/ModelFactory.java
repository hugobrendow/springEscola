package com.itau.escolaItauSpring;

import com.itau.escolaItauSpring.dto.request.VinculaCursoRequest;
import com.itau.escolaItauSpring.enums.NivelProfessorEnum;
import com.itau.escolaItauSpring.model.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelFactory {

    public static VinculaCursoRequest vinculaCursoRequest() {
        return VinculaCursoRequest.builder()
                .professorId(UUID.randomUUID())
                .cursoDisciplinaId(UUID.randomUUID())
                .titular(true)
                .build();
    }


    public static Professor professor() {

        Endereco endereco = new Endereco();
        Professor professor = new Professor();
        professor.setNome("Thiago");
        professor.setCpf("111111");
        professor.setEmail("asfasf@gmail.com");
        professor.setTelefone("00000");
        professor.setDataAdmissao(LocalDate.of(2022,10,19));
        professor.setEndereco(endereco);
        professor.setNivel(NivelProfessorEnum.ADJUNTO);

        return professor;
    }

    public static CursoDisciplina cursoDisciplina(){
        return CursoDisciplina
                .builder()
                .id(UUID.randomUUID())
                .curso(curso())
                .disciplina(disciplina())
                .build();
    }

    public static Disciplina disciplina() {
        return Disciplina.builder()
                .id(UUID.randomUUID())
                .nome("Disciplina")
                .cargaHoraria(20)
                .build();
    }

    public static Curso curso() {
        Curso curso = new Curso();
        curso.setId(UUID.randomUUID());
        curso.setNome("Nome");
        curso.setDescricao("Descricao");
        return curso;
    }
}
