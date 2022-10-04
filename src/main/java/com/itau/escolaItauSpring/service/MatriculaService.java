package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.request.MatriculaRequest;
import com.itau.escolaItauSpring.dto.response.MatriculaResponse;
import com.itau.escolaItauSpring.enums.StatusMatricula;
import com.itau.escolaItauSpring.exception.AlunoJaMatriculadoException;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.exception.NaoHaVagasException;
import com.itau.escolaItauSpring.mapper.MatriculaMapper;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.model.Matricula;
import com.itau.escolaItauSpring.model.Turma;
import com.itau.escolaItauSpring.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository repository;
    private final AlunoService alunoService;
    private final TurmaService turmaService;
    private final MatriculaMapper mapper;

    public MatriculaResponse matricular(MatriculaRequest request) throws NaoHaVagasException {
        Aluno aluno = alunoService.buscarModelPorId(request.getIdAluno());
        Turma turma = turmaService.buscarModelPorId(request.getIdTurma());

        UUID idTurma = turma.getId();

        validarMatriculaJaExistente(aluno.getId(), idTurma);
        validarSeHaVagas(idTurma);

        Matricula matriculaSalva = repository.save(gerarMatricula(aluno, turma));

        turmaService.atualizarVagas(idTurma);

        return mapper.toResponse(matriculaSalva);
    }

    private String gerarCodigoMatricula(LocalDate data){
        return new StringBuilder()
                .append(data.getYear())
                .append(data.getMonthValue())
                .append(data.getDayOfMonth())
                .append(repository.countByData(data))
                .toString();
    }

    private Matricula gerarMatricula(Aluno aluno, Turma turma){
        Matricula matricula = new Matricula(aluno, turma);
        matricula.setCodigo(gerarCodigoMatricula(matricula.getData()));
        return matricula;
    }

    private void validarSeHaVagas(UUID idTurma) {
        if (!turmaService.verificarSeHaVagas(idTurma))
            throw new NaoHaVagasException();
    }

    private void validarMatriculaJaExistente(UUID idAluno, UUID idTurma) {
        if (repository.findByAlunoIdAndTurmaId(idAluno, idTurma).isPresent())
            throw new AlunoJaMatriculadoException();
    }

    public Matricula buscarModelPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(ItemNaoExistenteException::new);
    }

    public MatriculaResponse buscarPorId(UUID id) {
        return mapper.toResponse(buscarModelPorId(id));
    }


    public Page<MatriculaResponse> listarPorTurmaPaginada(Pageable pageable, UUID idTurma) {
        Page<Matricula> matriculas = repository.findAllByTurmaId(pageable, idTurma);
        List<MatriculaResponse> matriculasPageable = mapper.toResponseList(matriculas.getContent());
        return new PageImpl<>(matriculasPageable, pageable, matriculas.getSize());
    }

    public List<MatriculaResponse> listarPorAluno(UUID idAluno) {
        List<Matricula> matriculas = repository.findAllByAlunoId(idAluno);
        return mapper.toResponseList(matriculas);
    }

    public MatriculaResponse trancarMatricula(UUID id) {
        return mapper.toResponse(alterarStatus(id,
                StatusMatricula.TRANCADA));
    }

    public MatriculaResponse ativarMatricula(UUID id) {
        return mapper.toResponse(alterarStatus(id, StatusMatricula.ATIVADA));
    }

    public MatriculaResponse cancelarMatricula(UUID id) {
        return mapper.toResponse(alterarStatus(id, StatusMatricula.CANCELADA));
    }

    private Matricula alterarStatus(UUID id, StatusMatricula status) {
        Matricula matricula = buscarModelPorId(id);
        matricula.setStatus(status);
        return repository.save(matricula);
    }
}
