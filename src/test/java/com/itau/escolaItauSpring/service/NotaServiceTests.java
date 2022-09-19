package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.NotaRequest;
import com.itau.escolaItauSpring.dto.response.NotaResponse;
import com.itau.escolaItauSpring.exception.NotaJaCadastradaException;
import com.itau.escolaItauSpring.mapper.NotaMapper;
import com.itau.escolaItauSpring.model.CursoDisciplina;
import com.itau.escolaItauSpring.model.Matricula;
import com.itau.escolaItauSpring.model.Nota;
import com.itau.escolaItauSpring.repository.NotaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NotaServiceTests {

    @InjectMocks
    private NotaService sut;

    @Mock
    private NotaMapper mapper;
    @Mock
    private NotaRepository repository;
    @Mock
    private MatriculaService matriculaService;
    @Mock
    private CursoDisciplinaService cursoDisciplinaService;

    private static UUID idCursoDisciplinaNotaCadastrada;
    private static UUID idMatriculaNotaCadastrada;

    private static UUID idCursoDisciplina;
    private static UUID idMatricula;

    private static NotaRequest  notaRequestParametrosCadastrados;
    private static NotaRequest  notaRequest;

    @BeforeEach
    public void init() {
        idCursoDisciplinaNotaCadastrada = UUID.fromString("67fd945c-fb0a-414f-9ee5-42c8dffad749");
        idMatriculaNotaCadastrada = UUID.fromString("be82b537-713f-4e00-a3c6-a4a72653096e");

        idCursoDisciplina = UUID.fromString("67fd945c-fb0a-414f-9ee5-42c8dffad740");
        idMatricula = UUID.fromString("be82b537-713f-4e00-a3c6-a4a72653096f");

        notaRequestParametrosCadastrados = new NotaRequest();
        notaRequestParametrosCadastrados.setCursoDisciplinaId(idCursoDisciplinaNotaCadastrada);
        notaRequestParametrosCadastrados.setMatriculaId(idMatriculaNotaCadastrada);

        notaRequest = new NotaRequest();
        notaRequest.setCursoDisciplinaId(idCursoDisciplina);
        notaRequest.setMatriculaId(idMatricula);
    }

    @Test
    @DisplayName("adicionar - lança NotaJaCadastradaException quando matricula ja possuir nota na disciplina")
    public void testAdicionar_lancaNotaJaCadastradaExceptionQuandoMatriculaJaPossuirNotaNaDisciplina() {

        var cursoDisciplinaResponse = new CursoDisciplina();
        cursoDisciplinaResponse.setId(idCursoDisciplinaNotaCadastrada);

        var matriculaResponse = new Matricula();
        matriculaResponse.setId(idMatriculaNotaCadastrada);

        when(repository.findByCursoDisciplinaIdAndMatriculaId(idCursoDisciplinaNotaCadastrada, idMatriculaNotaCadastrada))
                .thenReturn(Optional.of(new Nota()));
        when(cursoDisciplinaService.buscarModelPorId(any())).thenReturn(cursoDisciplinaResponse);
        when(matriculaService.buscarModelPorId(any())).thenReturn(matriculaResponse);


        assertThrows(NotaJaCadastradaException.class, () -> sut.adicionar(notaRequestParametrosCadastrados));
    }

    @Test
    @DisplayName("adicionar - lança NotaJaCadastradaException quando matricula ja possuir nota na disciplina")
    public void testAdicionar_retornaNotaResponseComDados() {

        var notaResponse = new NotaResponse();
        notaResponse.setMatriculaId(idMatricula);
        notaResponse.setCursoDisciplinaId(idCursoDisciplina);

        var nota = new Nota();
        nota.setId(UUID.randomUUID());

        var cursoDisciplinaResponse = new CursoDisciplina();
        cursoDisciplinaResponse.setId(idCursoDisciplina);

        var matriculaResponse = new Matricula();
        matriculaResponse.setId(idMatricula);

        when(repository.findByCursoDisciplinaIdAndMatriculaId(idCursoDisciplina, idMatricula))
                .thenReturn(Optional.empty());
        when(cursoDisciplinaService.buscarModelPorId(any())).thenReturn(cursoDisciplinaResponse);
        when(matriculaService.buscarModelPorId(any())).thenReturn(matriculaResponse);

        when(repository.save(any())).thenReturn(nota);

        when(mapper.toModel(any(), any(), any())).thenReturn(nota);
        when(mapper.toResponse(any(Nota.class))).thenReturn(notaResponse);


        var response = sut.adicionar(notaRequestParametrosCadastrados);

        assertNotNull(response);
        assertEquals(response.getMatriculaId(), idMatricula);
        assertEquals(response.getCursoDisciplinaId(), idCursoDisciplina);
        verify(repository, times(1)).save(any());
    }
}
