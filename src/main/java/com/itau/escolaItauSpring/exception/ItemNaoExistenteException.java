package com.itau.escolaItauSpring.exception;

public class ItemNaoExistenteException extends RuntimeException {
    public ItemNaoExistenteException() {
    }

    public ItemNaoExistenteException(String message) {
        super(message);
    }

    public ItemNaoExistenteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemNaoExistenteException(Throwable cause) {
        super(cause);
    }

    public ItemNaoExistenteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
