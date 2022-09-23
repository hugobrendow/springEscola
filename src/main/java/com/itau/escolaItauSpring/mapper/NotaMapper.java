package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.request.NotaRequest;
import com.itau.escolaItauSpring.dto.response.NotaResponse;
import com.itau.escolaItauSpring.model.CursoDisciplina;
import com.itau.escolaItauSpring.model.Matricula;
import com.itau.escolaItauSpring.model.Nota;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotaMapper {
    default Nota toModel(NotaRequest notaRequest, Matricula matricula, CursoDisciplina cursoDisciplina) {
        return new Nota(notaRequest.getNota(), matricula, cursoDisciplina);
    }

    @Mapping(source = "nota.matricula.id", target = "matriculaId")
    @Mapping(source = "nota.matricula.aluno.nome", target = "nomeAluno")
    @Mapping(source = "nota.cursoDisciplina.id", target = "cursoDisciplinaId")
    @Mapping(source = "nota.cursoDisciplina.curso.nome", target = "nomeCurso")
    @Mapping(source = "nota.cursoDisciplina.disciplina.nome", target = "nomeDisciplina")
    NotaResponse toResponse(Nota nota);

    List<NotaResponse> toResponseList(List<Nota> notas);

}
