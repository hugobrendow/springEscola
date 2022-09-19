package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.MatriculaRequest;
import com.itau.escolaItauSpring.dto.response.MatriculaResponse;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.mapper.MatriculaMapper;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.model.Turma;
import com.itau.escolaItauSpring.model.Matricula;
import com.itau.escolaItauSpring.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository repository;
    private final AlunoService alunoService;
    private final TurmaService turmaService;
    private final MatriculaMapper mapper;

    public MatriculaResponse matricular(MatriculaRequest request) {
        Aluno aluno = alunoService.buscarModelPorId(request.getIdAluno());
        Turma turma = turmaService.buscarModelPorId(request.getIdTurma());

        if (turmaService.validarSeHaVagas(turma.getId())) {

            Matricula matricula = new Matricula(aluno, turma);
            Matricula matriculaSalva = repository.save(matricula);
            return mapper.toResponse(matriculaSalva);
        }
        throw new RuntimeException();
    }

    public Matricula buscarModelPorId(UUID id){
        return repository.findById(id)
                .orElseThrow(ItemNaoExistenteException::new);
    }

    public MatriculaResponse buscarPorId(UUID id){
        return mapper.toResponse(buscarModelPorId(id));
    }

    public List<MatriculaResponse> listarPorTurma(UUID idTurma){
        List<Matricula> matriculas = repository.findAllByTurmaId(idTurma);
        return mapper.toResponseList(matriculas);
    }
}
