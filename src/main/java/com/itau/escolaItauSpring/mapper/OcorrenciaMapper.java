package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.request.OcorrenciaRequest;
import com.itau.escolaItauSpring.dto.response.OcorrenciaResponse;
import com.itau.escolaItauSpring.model.Ocorrencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OcorrenciaMapper {

    OcorrenciaResponse toResponse(Ocorrencia ocorrencia);

    @Mapping(source = "alunoId", target = "aluno.id")
    Ocorrencia toModel(OcorrenciaRequest ocorrenciaRequest);

    List<OcorrenciaResponse> toResponseList(List<Ocorrencia> ocorrenciaList);

}
