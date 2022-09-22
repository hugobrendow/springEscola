package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.OcorrenciaAlteracaoRequest;
import com.itau.escolaItauSpring.dto.request.OcorrenciaRequest;
import com.itau.escolaItauSpring.dto.response.OcorrenciaResponse;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.exception.OcorrenciaNaoEncontradaException;
import com.itau.escolaItauSpring.mapper.OcorrenciaMapper;
import com.itau.escolaItauSpring.model.Ocorrencia;
import com.itau.escolaItauSpring.repository.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;
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
        ocorrencia = ocorrenciaRepository.save(ocorrencia);
        return ocorrenciaMapper.toResponse(buscarOcorrenciaPorId(ocorrencia.getId()));
    }

    public OcorrenciaResponse alterarOcorrencia(UUID id, OcorrenciaAlteracaoRequest request) {
        Ocorrencia ocorrencia = buscarOcorrenciaPorId(id);
        ocorrencia.setDescricao(request.getDescricao());
        ocorrencia = ocorrenciaRepository.save(ocorrencia);
        return ocorrenciaMapper.toResponse(buscarOcorrenciaPorId(ocorrencia.getId()));
    }

    public void deletarOcorrencia(UUID id) {
        ocorrenciaRepository.deleteById(id);
    }

    public List<OcorrenciaResponse> listarOcorrenciasPorAluno(UUID id) {
        return ocorrenciaMapper.toResponseList(ocorrenciaRepository.findByAluno_Id(id));
    }

    private Ocorrencia buscarOcorrenciaPorId(UUID id) {
        return ocorrenciaRepository.findById(id).orElseThrow(ItemNaoExistenteException::new);
    }

}
