package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.request.AlunoRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.model.Aluno;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AlunoMapper {

    AlunoResponse toResponse(Aluno aluno);

    Aluno toModel(AlunoRequest alunoRequest);

    List<AlunoResponse> toResponseList(List<Aluno> alunos);

//    @InheritInverseConfiguration
//    CursoResponse campoToResponse(Curso curso);
//
//    @InheritInverseConfiguration
//    Curso requestToModel(CursoRequest cursoRequest);
}
