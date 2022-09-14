package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.request.MatriculaRequest;
import com.itau.escolaItauSpring.dto.response.MatriculaResponse;
import com.itau.escolaItauSpring.model.Matricula;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MatriculaMapper {

    MatriculaResponse toResponse(Matricula Matricula);

    Matricula toModel(MatriculaRequest MatriculaRequest);

    List<MatriculaResponse> toResponseList(List<Matricula> Matriculas);

}
