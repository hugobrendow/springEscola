package com.itau.escolaItauSpring.controller;

import com.itau.escolaItauSpring.dto.exception.CustomException;
import com.itau.escolaItauSpring.dto.request.VinculaCursoRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.dto.response.VinculaCursoResponse;
import com.itau.escolaItauSpring.service.ProfessorCursoDisciplinaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/professor-curso-disciplina")
@RequiredArgsConstructor
public class ProfessorCursoDisciplinaController {

    private final ProfessorCursoDisciplinaService professorCursoDisciplinaService;

    @ApiOperation(value = "Vincular professor ao curso")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Professor vinculado ao curso com sucesso", response = VinculaCursoResponse.class),
            @ApiResponse(code = 400, message = "Informações inválidas, verifique e tente novamente", response = CustomException.class),
            @ApiResponse(code = 401, message = "Usuário não possuí permissão para este método"),
            @ApiResponse(code = 500, message = "Erro interno", response = CustomException.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/vincula-curso")
    public ResponseEntity<VinculaCursoResponse> vincularCurso(@Valid @RequestBody VinculaCursoRequest vinculaCursoRequest){
        VinculaCursoResponse vinculaCursoResponse = professorCursoDisciplinaService.vincularCurso(vinculaCursoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(vinculaCursoResponse);
    }


}
