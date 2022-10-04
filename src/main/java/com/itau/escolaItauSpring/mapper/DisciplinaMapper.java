package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.response.DisciplinaResponse;
import com.itau.escolaItauSpring.model.Disciplina;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper {

    DisciplinaResponse toResponse(Disciplina disciplina);

}
