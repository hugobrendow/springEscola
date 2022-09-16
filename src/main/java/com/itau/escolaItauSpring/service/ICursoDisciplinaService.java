package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.model.CursoDisciplina;

import java.util.UUID;

public interface ICursoDisciplinaService {
    CursoDisciplina buscarPorId(UUID id);
}
