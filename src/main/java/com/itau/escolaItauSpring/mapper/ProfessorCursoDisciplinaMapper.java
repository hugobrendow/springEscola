package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.response.VinculaCursoResponse;
import com.itau.escolaItauSpring.model.ProfessorCursoDisciplina;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfessorCursoDisciplinaMapper {

    VinculaCursoResponse toResponse(ProfessorCursoDisciplina professorCursoDisciplina);

}
