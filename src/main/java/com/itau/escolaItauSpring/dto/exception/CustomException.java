package com.itau.escolaItauSpring.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomException {
    private List<String> mensagem;
    private Integer statusCode;
}
