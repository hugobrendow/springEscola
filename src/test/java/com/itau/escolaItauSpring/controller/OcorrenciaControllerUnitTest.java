package com.itau.escolaItauSpring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.UUID;

@WebMvcTest(OcorrenciaController.class)
public class OcorrenciaControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OcorrenciaService ocorrenciaService;

    @Autowired
    private ObjectMapper objectMapper;

    private String expectedSingleList;
    private String expected;
    private OcorrenciaRequest ocorrenciaRequest;
    private OcorrenciaResponse ocorrenciaResponse;
    private String idExistente;
    private String idInexistente;

    @BeforeEach
    void setUp() {

        expectedSingleList ="[{\"id\":\"65cb8254-2858-428a-9344-450fe37732d8\",\"descricao\":\"Teste descricao\",\"dataHora\":\"15-09-2022 17:03:00\",\"aluno\":{\"id\":null,\"nome\":null,\"dataNascimento\":null,\"telefone\":null,\"email\":null}}]";
        expected = "{\"id\":\"65cb8254-2858-428a-9344-450fe37732d8\",\"descricao\":\"Teste descricao\",\"dataHora\":\"15-09-2022 17:03:00\",\"aluno\":{\"id\":null,\"nome\":null,\"dataNascimento\":null,\"telefone\":null,\"email\":null}}";

        ocorrenciaRequest = new OcorrenciaRequest();
        ocorrenciaRequest.setAlunoId(UUID.fromString("65cb8254-2858-428a-9344-450fe37732d8"));
        ocorrenciaRequest.setDescricao("Teste descricao");
        ocorrenciaRequest.setDataHora(LocalDateTime.of(2022, 9, 15, 17, 3));

        ocorrenciaResponse = new OcorrenciaResponse();
        ocorrenciaResponse.setId(UUID.fromString("65cb8254-2858-428a-9344-450fe37732d8"));
        ocorrenciaResponse.setDescricao("Teste descricao");
        ocorrenciaResponse.setDataHora(LocalDateTime.of(2022, 9, 15, 17, 3));
        ocorrenciaResponse.setAluno(new AlunoResponse());

        idExistente = "65cb8254-2858-428a-9344-450fe37732d8";
        idInexistente = "00000000-0000-0000-0000-000000000000";

        when(ocorrenciaService.registrarOcorrencia(any(OcorrenciaRequest.class))).thenReturn(ocorrenciaResponse);
        when(ocorrenciaService.buscarOcorrencias()).thenReturn(Collections.singletonList(ocorrenciaResponse));
        when(ocorrenciaService.buscarOcorrencia(UUID.fromString(idExistente))).thenReturn(ocorrenciaResponse);
        when(ocorrenciaService.buscarOcorrencia(UUID.fromString(idInexistente))).thenThrow(OcorrenciaNaoEncontradaException.class);
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
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/ocorrencia/{id}", "65cb8254-2858-428a-9344-450fe37732d8").contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        Assertions.assertEquals(expected, result.andReturn().getResponse().getContentAsString());
    }

    @DisplayName(value = "Cadastrar nova ocorrencia")
    @Test
    void testeRegistrarOcorrencia() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(ocorrenciaRequest);

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
        String jsonBody = objectMapper.writeValueAsString(ocorrenciaRequest);

        ResultActions result = this.mockMvc.perform(put("/ocorrencia/{id}", idExistente)
                .content(jsonBody)
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
