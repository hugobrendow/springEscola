package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.model.CursoDisciplina;
import com.itau.escolaItauSpring.repository.CursoDisciplinaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CursoDisciplinaServiceTests {

    @InjectMocks
    private CursoDisciplinaService cursoDisciplinaService;

    @Mock
    private CursoDisciplinaRepository cursoDisciplinaRepositoryMock;

    @Test
    void aoBuscarPorIdDeveRetornarSemErro() {
        Optional<CursoDisciplina> cursoDisciplinaEsperado = Optional.of(new CursoDisciplina());
        when(cursoDisciplinaRepositoryMock.findById(any(UUID.class))).thenReturn(cursoDisciplinaEsperado);
        CursoDisciplina cursoDisciplinaRetornado = cursoDisciplinaService.buscarPorId(UUID.randomUUID());

        assertEquals(cursoDisciplinaEsperado.get(), cursoDisciplinaRetornado);
    }

    @Test
    void aoBuscarPorIdDeveRetornarItemNaoExistenteException() {
        when(cursoDisciplinaRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrowsExactly(ItemNaoExistenteException.class, () -> cursoDisciplinaService.buscarPorId(UUID.randomUUID()));
    }

}
