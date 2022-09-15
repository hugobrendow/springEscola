package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.OcorrenciaRequest;
import com.itau.escolaItauSpring.dto.response.OcorrenciaResponse;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.exception.OcorrenciaNaoEncontradaException;
import com.itau.escolaItauSpring.mapper.OcorrenciaMapper;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.model.Ocorrencia;
import com.itau.escolaItauSpring.repository.AlunoRepository;
import com.itau.escolaItauSpring.repository.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;
    private final AlunoRepository alunoRepository;
    private final OcorrenciaMapper ocorrenciaMapper;

    public List<OcorrenciaResponse> buscarOcorrencias() {
        List<Ocorrencia> ocorrencia = ocorrenciaRepository.findAll();
        return ocorrenciaMapper.toResponseList(ocorrencia);
    }

    public OcorrenciaResponse buscarOcorrencia(UUID id) {
        Ocorrencia ocorrencia = buscarOcorrenciaPorId(id);
        return ocorrenciaMapper.toResponse(ocorrencia);
    }

    public OcorrenciaResponse registrarOcorrencia(OcorrenciaRequest request) {
        Ocorrencia ocorrencia = ocorrenciaMapper.toModel(request);
        Aluno aluno = buscarAlunoPorId(request.getAlunoId());
        ocorrencia.setAluno(aluno);
        return ocorrenciaMapper.toResponse(ocorrenciaRepository.save(ocorrencia));
    }

    public OcorrenciaResponse alterarOcorrencia(UUID id, OcorrenciaRequest request) {
        Ocorrencia ocorrencia = ocorrenciaMapper.toModel(request);
        Aluno aluno = buscarAlunoPorId(request.getAlunoId());
        ocorrencia.setAluno(aluno);
        ocorrencia.setId(id);
        return ocorrenciaMapper.toResponse(ocorrenciaRepository.save(ocorrencia));
    }

    public void deletarOcorrencia(UUID id) {
        ocorrenciaRepository.deleteById(id);
    }

    private Aluno buscarAlunoPorId(UUID id) {
        return alunoRepository.findById(id).orElseThrow(ItemNaoExistenteException::new);
    }

    private Ocorrencia buscarOcorrenciaPorId(UUID id) {
        return ocorrenciaRepository.findById(id).orElseThrow(OcorrenciaNaoEncontradaException::new);
    }

}
