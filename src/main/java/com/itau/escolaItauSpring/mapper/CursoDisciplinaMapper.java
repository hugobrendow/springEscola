package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.response.CursoDisciplinaResponse;
import com.itau.escolaItauSpring.model.CursoDisciplina;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CursoDisciplinaMapper {

    CursoDisciplinaResponse toResponse(CursoDisciplina cursoDisciplina);

}
