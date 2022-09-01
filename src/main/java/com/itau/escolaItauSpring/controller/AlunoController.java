package com.itau.escolaItauSpring.controller;


import com.itau.escolaItauSpring.dto.request.AlunoRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.model.Endereco;
import com.itau.escolaItauSpring.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoResponse>>aluno(){
        return ResponseEntity.ok(alunoService.listar());
    }

    @PostMapping()
    public ResponseEntity<AlunoResponse> cadastrar(
            @RequestBody AlunoRequest alunoRequest,
            UriComponentsBuilder uriComponentsBuilder
    ){
        AlunoResponse alunoResponse = alunoService.adicionar(alunoRequest);
        URI uri = uriComponentsBuilder.path("/aluno/{id}").buildAndExpand(alunoResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(alunoResponse);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> buscarAluno(@PathVariable UUID id) throws ItemNaoExistenteException {
        return ResponseEntity.ok(alunoService.localizar(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> ativarAluno(@PathVariable UUID id) throws ItemNaoExistenteException {
        alunoService.ativar(id);
        return ResponseEntity.accepted().body("Aluno ativado");
    }

    @GetMapping("/quantidade-ativo")
    public ResponseEntity<Long> listarQuantidadeAtivos() {
        return ResponseEntity.ok().body(alunoService.quantidadeAlunosAtivo());
    }

    @DeleteMapping("/cpf/{cpf}")
    public ResponseEntity removerPorCpf(@PathVariable Long cpf) {
        alunoService.removerPorCpf(cpf);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/busca/{nome}")
    public ResponseEntity buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(alunoService.buscarPorNome(nome));
    }
}
