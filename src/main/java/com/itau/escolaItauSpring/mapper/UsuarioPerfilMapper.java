package com.itau.escolaItauSpring.mapper;

import com.itau.escolaItauSpring.dto.request.UsuarioPerfilRequest;
import com.itau.escolaItauSpring.dto.response.PerfilResponse;
import com.itau.escolaItauSpring.dto.response.UsuarioPerfilResponse;
import com.itau.escolaItauSpring.dto.response.UsuarioResponse;
import com.itau.escolaItauSpring.model.Perfil;
import com.itau.escolaItauSpring.model.Usuario;
import com.itau.escolaItauSpring.model.UsuarioPerfil;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioPerfilMapper {
    UsuarioPerfil toModel(UsuarioPerfilRequest usuarioPerfis);

    UsuarioPerfilResponse toResponse(UsuarioPerfil usuarioPerfis);

    List<UsuarioPerfilResponse> toResponseList(List<UsuarioPerfil> usuariosPerfis);

    Usuario map(UsuarioResponse value);

    Perfil map(PerfilResponse value);
}
