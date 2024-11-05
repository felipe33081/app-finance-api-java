package com.dev.estacio.finance.exception;

import java.io.Serial;

public class ObjectNotNullException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ObjectNotNullException(String message) {
        super(message);
    }

}
