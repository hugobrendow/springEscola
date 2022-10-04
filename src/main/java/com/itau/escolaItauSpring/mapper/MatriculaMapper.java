package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.response.MatriculaResponse;
import com.itau.escolaItauSpring.model.Matricula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MatriculaMapper {

    @Mapping(source = "matricula.aluno.id", target = "idAluno")
    @Mapping(source = "matricula.turma.id", target = "idTurma")
    MatriculaResponse toResponse(Matricula matricula);

    List<MatriculaResponse> toResponseList(List<Matricula> matriculas);

}
