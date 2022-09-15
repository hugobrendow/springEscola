package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.request.AlunoRequest;
import com.itau.escolaItauSpring.dto.request.OcorrenciaRequest;
import com.itau.escolaItauSpring.dto.response.OcorrenciaResponse;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.model.Ocorrencia;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OcorrenciaMapper {

    OcorrenciaResponse toResponse(Ocorrencia ocorrencia);

    Ocorrencia toModel(OcorrenciaRequest ocorrenciaRequest);

    List<OcorrenciaResponse> toResponseList(List<Ocorrencia> ocorrenciaList);

}
