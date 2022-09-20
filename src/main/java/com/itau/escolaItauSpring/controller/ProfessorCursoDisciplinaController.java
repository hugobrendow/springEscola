package com.itau.escolaItauSpring.controller;

import com.itau.escolaItauSpring.dto.request.VinculaCursoRequest;
import com.itau.escolaItauSpring.dto.response.VinculaCursoResponse;
import com.itau.escolaItauSpring.service.ProfessorCursoDisciplinaService;
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

    @PostMapping("/vincula-curso")
    public ResponseEntity<?> vincularCurso(@Valid @RequestBody VinculaCursoRequest vinculaCursoRequest){
        VinculaCursoResponse vinculaCursoResponse = professorCursoDisciplinaService.vincularCurso(vinculaCursoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(vinculaCursoResponse);
    }


}
