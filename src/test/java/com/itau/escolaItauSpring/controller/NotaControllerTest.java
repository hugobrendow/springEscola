package com.itau.escolaItauSpring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.escolaItauSpring.dto.request.NotaRequest;
import com.itau.escolaItauSpring.dto.response.NotaResponse;
import com.itau.escolaItauSpring.service.NotaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotaController.class)
@ContextConfiguration
@WebAppConfiguration
class NotaControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NotaService notaService;

    @Autowired
    private WebApplicationContext context;


    @Test
    @WithMockUser(roles = "COORDENADOR", username = "jack", password = "admin")
    public void testeCadastrarNota() throws Exception {

        NotaRequest notaRequest = new NotaRequest();
        notaRequest.setMatriculaId(UUID.fromString("d9632725-f147-43f8-bc01-679fbc780297"));
        notaRequest.setCursoDisciplinaId(UUID.fromString("0be5868c-4b59-474e-a185-a1290b19b23e"));
        notaRequest.setNota(BigDecimal.valueOf(7.0));


        String jsonBody = objectMapper.writeValueAsString(notaRequest);

        NotaResponse notaResponse = new NotaResponse();
        notaResponse.setId(UUID.fromString("0be5812c-4b59-474e-a185-a1290b19b23e"));

        Mockito.when(notaService.adicionar(notaRequest)).thenReturn(notaResponse);

        ResultActions result = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build().perform(post("/nota")
                        .with(httpBasic("jack", "admin")).with(csrf())
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(header().exists("Location"));
//                .andExpect(header().string("Location", (a)->{
//                    MatcherAssert.assertThat(true, StringContains.("0be5812c-4b59-474e-a185-a1290b19b23e").matches(a));
//                }));

    }

}
