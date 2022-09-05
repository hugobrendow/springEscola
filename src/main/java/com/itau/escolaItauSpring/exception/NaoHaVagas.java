package com.itau.escolaItauSpring.exception;

public class NaoHaVagas extends Exception {
    public NaoHaVagas() {
        super("Não há vagas disponíveis");
    }
}
