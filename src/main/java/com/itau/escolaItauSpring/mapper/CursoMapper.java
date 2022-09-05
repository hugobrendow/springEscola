package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.request.CursoRequest;
import com.itau.escolaItauSpring.dto.response.CursoResponse;
import com.itau.escolaItauSpring.model.Curso;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CursoMapper {
    CursoResponse toResponse(Curso curso);

    Curso toModel(CursoRequest curso);

    List<CursoResponse> toResponseList(List<Curso> cursos);
}
