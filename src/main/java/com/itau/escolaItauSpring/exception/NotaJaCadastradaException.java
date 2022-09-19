package com.itau.escolaItauSpring.exception;

public class NotaJaCadastradaException extends RuntimeException {
    public NotaJaCadastradaException(){
        super("Nota já cadastrada para esta Matricula nessa Disciplina, favor verificar");
    }
}
