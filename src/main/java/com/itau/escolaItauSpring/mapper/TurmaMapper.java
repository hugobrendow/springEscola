package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.request.CursoTurmaRequest;
import com.itau.escolaItauSpring.dto.request.TurmaRequest;
import com.itau.escolaItauSpring.dto.response.CursoResponse;
import com.itau.escolaItauSpring.dto.response.TurmaResponse;
import com.itau.escolaItauSpring.model.Curso;
import com.itau.escolaItauSpring.model.Turma;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TurmaMapper {

    TurmaResponse toResponse(Turma turma);

    Turma toModel(TurmaRequest turmaRequest);
    Curso map(CursoTurmaRequest value);

    // List<TurmaResponse> toResponseList(List<Turma> turmas);

    @InheritInverseConfiguration
    CursoResponse campoToResponse(Curso curso);

//    @InheritInverseConfiguration
//    Curso requestToModel(CursoRequest cursoRequest);
}
