package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.EnderecoRequest;
import com.itau.escolaItauSpring.dto.request.ProfessorRequest;
import com.itau.escolaItauSpring.dto.response.ProfessorResponse;
import com.itau.escolaItauSpring.enums.NivelProfessorEnum;
import com.itau.escolaItauSpring.mapper.ProfessorMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
// @ContextConfiguration(classes = { ProfessorService.class, ProfessorRepository.class, ProfessorMapperImpl.class})
@SpringBootTest
public class ProfessorServiceIntegrationTest {

    @Autowired
    private ProfessorService service;

    @Autowired
    private ProfessorMapper professorMapper;

    @AfterEach
    private void clearDb(){
        service.limparBanco();
    }

    @Test
    public void adicionarTest(){
        service.adicionar(buildProfessorRequest());
        Pageable pageable = PageRequest.of(0,5);
        Assertions.assertEquals(1, service.listar(pageable).size());
    }
    @Test
    public void listarTest() {
        Pageable pageRequest = PageRequest.of(0, 5);
        Assertions.assertEquals(0,service.listar(pageRequest).size());
        var id = service.adicionar(buildProfessorRequest()).getId();
        Assertions.assertEquals(1,service.listar(pageRequest).size());
        service.removerProfessor(id);
        Assertions.assertEquals(0,service.listar(pageRequest).size());
    }
    @Test
    public void removerProfessorTest() {

        ProfessorRequest professorRequest = buildProfessorRequest();

        ProfessorResponse professorResponse = service.adicionar(professorRequest);

        Pageable pageable = PageRequest.of(0, 5);

        Assertions.assertEquals(1, service.listar(pageable).size());

        UUID professorId = professorResponse.getId();

        service.removerProfessor(professorId);

        Assertions.assertEquals(0, service.listar(pageable).size());

    }

    @Test
    public void buscarPorIdTest() {

        ProfessorRequest professorRequest = buildProfessorRequest();

        ProfessorResponse professorResponse = service.adicionar(professorRequest);

        UUID professorId = professorResponse.getId();

        ProfessorResponse professorResponseEncontrado = service.buscarPorId(professorId);

        UUID professorResponseEncontradoId = professorResponseEncontrado.getId();

        Assertions.assertEquals(professorId, professorResponseEncontradoId);

    }


    @Test
    public void atualizarTest(){
        ProfessorRequest professorRequest = buildProfessorRequest();
        ProfessorResponse professorAdicionado =  service.adicionar(professorRequest);
        UUID idProfessorAdicionado = professorAdicionado.getId();
        ProfessorRequest professorRequest2 = buildProfessorRequest2();
        service.atualizar(idProfessorAdicionado, professorRequest2);
        ProfessorResponse professorAtualizado = service.buscarPorId(idProfessorAdicionado);
        Assertions.assertEquals(professorRequest2.getNome(), professorAtualizado.getNome());
    }

    @Test
    public void filtroTest() {
        service.adicionar(buildProfessorRequest());
        service.adicionar(buildProfessorRequest2());
        Pageable pageable = PageRequest.of(0,5);
        var result = service.listar(pageable);
        System.out.println(result);

        List<ProfessorResponse> listProfessorResponse1 = service.filtro("Pro", null, null, pageable);
        Assertions.assertEquals("Professor Tiburcio", listProfessorResponse1.get(0).getNome());

        var listProfessorResponse2 = service.filtro(null, "1234", null, pageable);
        Assertions.assertEquals(listProfessorResponse2.get(0).getNome(), "Professor Tiburcio");

        var listProfessorResponse3 = service.filtro(null, null, NivelProfessorEnum.ADJUNTO, pageable);
        Assertions.assertEquals(listProfessorResponse3.get(0).getNome(), "Professor Tiburcio");
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

    private ProfessorRequest buildProfessorRequest2() {
        return ProfessorRequest.builder()
                .nome("Clotilde Arraia")
                .cpf("987654321")
                .email("bruxa71@gmail.com")
                .telefone("65498731")
                .dataAdmissao(LocalDate.now().minusDays(1))
                .nivel(NivelProfessorEnum.ASSISTENTE)
                .endereco(
                        EnderecoRequest.builder()
                                .logradouro("rua das laranjeiras")
                                .cep("998754-258")
                                .complemento("do lado da casa do zé")
                                .cidade("Holambra")
                                .estado("Para")
                                .build()
                )
                .build();

    }
}

