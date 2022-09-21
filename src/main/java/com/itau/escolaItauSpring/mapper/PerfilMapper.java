package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.request.PerfilRequest;
import com.itau.escolaItauSpring.dto.response.PerfilResponse;
import com.itau.escolaItauSpring.model.Perfil;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PerfilMapper {
    Perfil toModel(PerfilRequest perfil);

    PerfilResponse toResponse(Perfil perfil);

    List<PerfilResponse> toResponseList(List<Perfil> perfils);
}
