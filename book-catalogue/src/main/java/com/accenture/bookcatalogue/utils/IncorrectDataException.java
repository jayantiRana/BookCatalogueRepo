package com.accenture.bookcatalogue.utils;

public class IncorrectDataException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IncorrectDataException() {
        super();
    }

    public IncorrectDataException(String message) {
        super(message);
    }
}
