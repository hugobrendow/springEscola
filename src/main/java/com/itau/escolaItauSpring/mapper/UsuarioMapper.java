package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.request.UsuarioRequest;
import com.itau.escolaItauSpring.dto.response.UsuarioResponse;
import com.itau.escolaItauSpring.model.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toModel(UsuarioRequest usuario);

    UsuarioResponse toResponse(Usuario usuario);

    List<UsuarioResponse> toResponseList(List<Usuario> usuarios);
}
