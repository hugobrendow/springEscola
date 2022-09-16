package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.request.NotaRequest;
import com.itau.escolaItauSpring.dto.response.NotaResponse;
import com.itau.escolaItauSpring.model.CursoDisciplina;
import com.itau.escolaItauSpring.model.Matricula;
import com.itau.escolaItauSpring.model.Nota;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;


@Mapper(componentModel = "spring")
public interface NotaMapper {

    NotaResponse toResponse(Nota nota);

    Nota toModel(NotaRequest notaRequest);
    Matricula mapMatricula(UUID matricula);
    CursoDisciplina mapCursoDisciplina(UUID disciplina);

    List<NotaResponse> toResponseList(List<Nota> notas);

}
