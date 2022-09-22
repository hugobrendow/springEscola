package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.model.CursoDisciplina;
import com.itau.escolaItauSpring.repository.CursoDisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CursoDisciplinaService {

    private final CursoDisciplinaRepository repository;

    public CursoDisciplina buscarModelPorId(UUID id) {
        return repository.findById(id).orElseThrow(ItemNaoExistenteException::new);
    }
}
