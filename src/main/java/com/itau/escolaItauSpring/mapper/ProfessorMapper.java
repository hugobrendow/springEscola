package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.request.ProfessorRequest;
import com.itau.escolaItauSpring.dto.response.ProfessorResponse;
import com.itau.escolaItauSpring.model.Professor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    ProfessorResponse toResponse(Professor professor);
    
    Professor toModel(ProfessorRequest professorRequest);

    List<ProfessorResponse> toResponseList(List<Professor> professores);
}
