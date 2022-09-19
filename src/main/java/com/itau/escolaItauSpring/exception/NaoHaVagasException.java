package com.itau.escolaItauSpring.exception;

public class NaoHaVagasException extends RuntimeException {
    public NaoHaVagasException() {
        super("Não há vagas disponíveis");
    }
}
