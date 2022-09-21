package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.EnderecoRequest;
import com.itau.escolaItauSpring.dto.request.ProfessorRequest;
import com.itau.escolaItauSpring.dto.response.ProfessorResponse;
import com.itau.escolaItauSpring.enums.NivelProfessorEnum;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.mapper.ProfessorMapper;
import com.itau.escolaItauSpring.model.Professor;
import com.itau.escolaItauSpring.repository.ProfessorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ProfessorServiceUnitTest {
    @Mock
    private ProfessorMapper professorMapper;
    @Mock
    private ProfessorRepository professorRepository;
    @InjectMocks
    private ProfessorService professorService;

    @Test
    // @DisplayName("Testa adicao professor")
    public void adicionarTest() {
        var professor = new Professor();

        Mockito.when(professorMapper.toModel(Mockito.any(ProfessorRequest.class))).thenReturn(professor);
        Mockito.when(professorMapper.toResponse(ArgumentMatchers.any(Professor.class))).thenReturn(new ProfessorResponse());
        Mockito.when(professorRepository.save(ArgumentMatchers.any(Professor.class))).thenReturn(professor);

        professorService.adicionar(buildProfessorRequest());

        Mockito.verify(professorRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(professorMapper, Mockito.times(1)).toResponse(Mockito.any());
    }

    @Test
    // @DisplayName("Testa a listagem dos professores")
    public void listarTest() {

        Pageable pageable = Mockito.mock(Pageable.class);

        Page<Professor> professorPage = Mockito.mock(Page.class);
        Mockito.when(professorRepository.findAll(Mockito.any(Pageable.class))).thenReturn(professorPage);

        List<ProfessorResponse> professorResponseList = new ArrayList<>();
        ProfessorResponse professorResponse = new ProfessorResponse();

        professorResponseList.add(professorResponse);
        Mockito.when(professorMapper.toResponseList(Mockito.any(List.class))).thenReturn(professorResponseList);

        List<ProfessorResponse> professores = professorService.listar(pageable);

        Assertions.assertInstanceOf(ProfessorResponse.class, professores.get(0));
        Assertions.assertEquals(1,professores.size());
    }

    @Test
    public void atualizarTest() throws ItemNaoExistenteException {
        Professor professor = new Professor();
        Professor professorPosToModel = new Professor();
        ProfessorRequest professorRequest = buildProfessorRequest();
        ProfessorResponse professorPosToResponse = new ProfessorResponse();
        UUID id = UUID.randomUUID();
        professorPosToResponse.setId(id);
        professor.setId(id);

        Mockito.when(professorRepository.findById(id)).thenReturn(Optional.of(professor));
        Mockito.when(professorRepository.save(professorPosToModel)).thenReturn(professorPosToModel);
        Mockito.when(professorMapper.toModel(professorRequest)).thenReturn(professorPosToModel);
        Mockito.when(professorMapper.toResponse(professorPosToModel)).thenReturn(professorPosToResponse);

        ProfessorResponse professorResponseReturned = professorService.atualizar(id, professorRequest);

        Assertions.assertEquals(professorResponseReturned.getId(), id);
        Assertions.assertNotNull(professorResponseReturned);
    }

    @Test
    public void removeProfessorTest() {
        Professor professor = new Professor();
        professor.setId(UUID.randomUUID());
        Mockito.when(professorRepository.findById(professor.getId())).thenReturn(Optional.of(professor));
        professorService.removerProfessor(professor.getId());
        Mockito.verify(professorRepository, Mockito.times(1)).delete(professor);
    }

    @Test
    public void buscarPorIdTest() {
        ProfessorResponse professorResponse = new ProfessorResponse();
        Professor professor = new Professor();
        Mockito.when(professorRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(professor));
        Mockito.when(professorMapper.toResponse(professor)).thenReturn(professorResponse);
        ProfessorResponse professorResponseActual = professorService.buscarPorId(UUID.randomUUID());
        Assertions.assertEquals(professorResponse, professorResponseActual);
    }

    @Test
    public void buscarPorFiltroTest() {
        Pageable pageable = Mockito.mock(Pageable.class);
        Page<Professor> professorPage = Mockito.mock(Page.class);

        Mockito.when(professorRepository.findProfessorByNomeContainingIgnoreCaseOrCpfContainingOrNivel(any(String.class), any(String.class), any(NivelProfessorEnum.class), any(Pageable.class))).thenReturn(professorPage);

        List<ProfessorResponse> professorResponseList = new ArrayList<>();
        ProfessorResponse professorResponse = new ProfessorResponse();
        professorResponseList.add(professorResponse);

        Mockito.when(professorMapper.toResponseList(any(List.class))).thenReturn(professorResponseList);

        List<ProfessorResponse> professorResponseListResult1 = professorService.filtro("Cl", "", NivelProfessorEnum.ADJUNTO, pageable);
        List<ProfessorResponse> professorResponseListResult2 = professorService.filtro("" , "12",NivelProfessorEnum.ADJUNTO, pageable);
        List<ProfessorResponse> professorResponseListResult3 = professorService.filtro("", "", NivelProfessorEnum.ADJUNTO, pageable);

        Assertions.assertNotNull(professorResponseListResult1);
        Assertions.assertNotNull(professorResponseListResult2);
        Assertions.assertNotNull(professorResponseListResult3);

        Assertions.assertInstanceOf(ProfessorResponse.class, professorResponseListResult1.get(0));
        Assertions.assertInstanceOf(ProfessorResponse.class, professorResponseListResult2.get(0));
        Assertions.assertInstanceOf(ProfessorResponse.class, professorResponseListResult3.get(0));

    }

    private ProfessorRequest buildProfessorRequest() {
        return ProfessorRequest.builder()
                .nome("Professor Tiburcio")
                .cpf("12345689")
                .email("professor@gmail.com")
                .telefone("977885522")
                .dataAdmissao(LocalDate.now())
                .nivel(NivelProfessorEnum.ADJUNTO)
                .endereco(
                        EnderecoRequest.builder()
                                .logradouro("rua das ameixeiras")
                                .cep("03369-250")
                                .complemento("na rua da feira de domingo")
                                .cidade("cubatão")
                                .estado("São Paulo")
                                .build()
                )
                .build();

    }
}
