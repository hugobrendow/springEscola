package com.itau.escolaItauSpring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.escolaItauSpring.dto.request.OcorrenciaAlteracaoRequest;
import com.itau.escolaItauSpring.dto.request.OcorrenciaRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.dto.response.OcorrenciaResponse;
import com.itau.escolaItauSpring.exception.OcorrenciaNaoEncontradaException;
import com.itau.escolaItauSpring.service.OcorrenciaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@WebMvcTest(OcorrenciaController.class)
public class OcorrenciaControllerUnitTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public OcorrenciaControllerUnitTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @MockBean
    OcorrenciaService ocorrenciaService;


    private String expectedSingleList;
    private String expected;
    private OcorrenciaRequest ocorrenciaRequest;
    private OcorrenciaAlteracaoRequest ocorrenciaAlteracaoRequest;
    private OcorrenciaResponse ocorrenciaResponse;
    private String idExistente;
    private String idInexistente;
    private String jsonBody;
    private String jsonAlteradoBody;

    @BeforeEach
    void setUp() throws JsonProcessingException {

        idExistente = "65cb8254-2858-428a-9344-450fe37732d8";
        idInexistente = "00000000-0000-0000-0000-000000000000";

        ocorrenciaRequest = new OcorrenciaRequest();
        ocorrenciaRequest.setAlunoId(UUID.fromString(idExistente));
        ocorrenciaRequest.setDescricao("Teste descricao");

        ocorrenciaAlteracaoRequest = new OcorrenciaAlteracaoRequest();
        ocorrenciaRequest.setDescricao("Teste alterar descricao");

        ocorrenciaResponse = new OcorrenciaResponse();
        ocorrenciaResponse.setId(UUID.fromString(idExistente));
        ocorrenciaResponse.setDescricao("Teste descricao");
        ocorrenciaResponse.setDataHora(LocalDateTime.of(2022, 9, 15, 17, 3));
        ocorrenciaResponse.setAluno(new AlunoResponse());

        expectedSingleList = objectMapper.writeValueAsString(List.of(ocorrenciaResponse));
        expected = objectMapper.writeValueAsString(ocorrenciaResponse);
        jsonBody = objectMapper.writeValueAsString(ocorrenciaRequest);
        jsonAlteradoBody = objectMapper.writeValueAsString(ocorrenciaAlteracaoRequest);

        when(ocorrenciaService.registrarOcorrencia(any(OcorrenciaRequest.class))).thenReturn(ocorrenciaResponse);
        when(ocorrenciaService.buscarOcorrencias()).thenReturn(Collections.singletonList(ocorrenciaResponse));
        when(ocorrenciaService.buscarOcorrencia(UUID.fromString(idExistente))).thenReturn(ocorrenciaResponse);
        when(ocorrenciaService.buscarOcorrencia(UUID.fromString(idInexistente))).thenThrow(OcorrenciaNaoEncontradaException.class);
        when(ocorrenciaService.alterarOcorrencia(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(ocorrenciaResponse);
    }

    @DisplayName(value = "Listar todas as ocorrências")
    @Test
    void testeBuscarOcorrencias() throws Exception {

        var result = this.mockMvc.perform(MockMvcRequestBuilders.get("/ocorrencia")).andReturn();

        Mockito.verify(ocorrenciaService, Mockito.times(1)).buscarOcorrencias();
        Assertions.assertEquals(expectedSingleList, result.getResponse().getContentAsString());
        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

    @DisplayName(value = "Buscar ocorrência por ID")
    @Test
    void testeBuscarOcorrencia() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/ocorrencia/{id}", idExistente).contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        Assertions.assertEquals(expected, result.andReturn().getResponse().getContentAsString());
    }

    @DisplayName(value = "Cadastrar nova ocorrencia")
    @Test
    void testeRegistrarOcorrencia() throws Exception {

        ResultActions result = this.mockMvc.perform(post("/ocorrencia")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
        Assertions.assertEquals(expected, result.andReturn().getResponse().getContentAsString());
        Mockito.verify(ocorrenciaService, Mockito.times(1)).registrarOcorrencia(ArgumentMatchers.any(OcorrenciaRequest.class));
    }

    @DisplayName(value = "Editar ocorrencia")
    @Test
    void testeEditarOcorrencia() throws Exception {

        ResultActions result = this.mockMvc.perform(patch("/ocorrencia/{id}", idExistente)
                .content(jsonAlteradoBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @DisplayName(value = "Deletar ocorrência por ID")
    @Test
    void testeDeletarOcorrencia() throws Exception {
        doNothing().when(ocorrenciaService).deletarOcorrencia(any());

        MvcResult result = this.mockMvc.perform(delete("/ocorrencia/{id}", UUID.fromString(idExistente)))
                .andExpect(status().isNoContent())
                .andReturn();

        verify(ocorrenciaService, times(1)).deletarOcorrencia(UUID.fromString(idExistente));
    }

}
