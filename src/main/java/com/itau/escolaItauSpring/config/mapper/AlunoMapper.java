package com.itau.escolaItauSpring.config.mapper;

import com.itau.escolaItauSpring.dto.request.AlunoRequest;
import com.itau.escolaItauSpring.dto.request.CursoRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.dto.response.CursoResponse;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.model.Curso;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AlunoMapper {

    AlunoResponse toResponse(Aluno aluno);

    Aluno toModel(AlunoRequest alunoRequest);

    List<AlunoResponse> mapAluno(List<Aluno> alunos);

    @InheritInverseConfiguration
    CursoResponse campoToResponse(Curso curso);

    @InheritInverseConfiguration
    Curso requestToModel(CursoRequest cursoRequest);
}
