package com.website.ecommerce.exception;

import java.util.HashMap;

public class HandleException extends RuntimeException {
    private final HashMap<String, String> errors;

    public HandleException(HashMap<String, String> errors) {
        this.errors = errors;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }
}
