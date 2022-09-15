package com.itau.escolaItauSpring.exception;

public class OcorrenciaNaoEncontradaException extends RuntimeException {
    public OcorrenciaNaoEncontradaException() {
        super("A ocorrência solicitada não foi encontrada!");
    }
}
