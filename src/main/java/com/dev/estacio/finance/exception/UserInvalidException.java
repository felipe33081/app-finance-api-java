package com.dev.estacio.finance.exception;

import java.io.Serial;

public class UserInvalidException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserInvalidException(String message) {
        super(message);
    }

}
