package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.request.MatriculaRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.dto.response.CursoResponse;
import com.itau.escolaItauSpring.dto.response.MatriculaResponse;
import com.itau.escolaItauSpring.dto.response.TurmaResponse;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.model.Turma;
import com.itau.escolaItauSpring.model.Matricula;
import org.mapstruct.InheritInverseConfiguration;
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
