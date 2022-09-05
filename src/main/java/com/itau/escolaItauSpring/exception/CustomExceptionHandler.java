package com.itau.escolaItauSpring.exception;

import com.itau.escolaItauSpring.dto.exception.CustomException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolationException(
            RuntimeException ex, WebRequest request) {
        CustomException exceptionDetail = new CustomException(List.of("Problema ao inserir registro"), HttpStatus.CONFLICT.value());
        return handleExceptionInternal(ex, exceptionDetail,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> detalhes = ex.getBindingResult().getAllErrors().stream()
                .map(obj -> obj.getDefaultMessage())
                .collect(Collectors.toList());
        return handleExceptionInternal(ex, new CustomException(detalhes, HttpStatus.BAD_REQUEST.value()),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
