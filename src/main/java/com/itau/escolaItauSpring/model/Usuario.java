package com.itau.escolaItauSpring.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private Boolean accountNonExpired = true;

    private Boolean accountNonLocked = true;

    private Boolean credentialsNonExpired = true;

    private Boolean enabled = true;

    @OneToMany(mappedBy = "usuario")
    private Set<UsuarioPerfil> usuarioPerfis;

    public UserDetails getUserDetails(){
        UserDetails user = new User(getUsername(), getPassword(), getEnabled(),
        getAccountNonExpired(), getCredentialsNonExpired(),
        getAccountNonLocked(), convertToAuthorities());
        return user;
    }

    private Set<? extends GrantedAuthority> convertToAuthorities() {
        if(CollectionUtils.isEmpty(usuarioPerfis)) {
            return Collections.emptySet();
        }

        return usuarioPerfis.stream()
                .map(UsuarioPerfil::getPerfil)
                .map(Perfil::getPermissao)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
