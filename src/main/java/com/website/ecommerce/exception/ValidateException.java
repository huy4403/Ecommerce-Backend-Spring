package com.website.ecommerce.exception;

import java.util.HashMap;

public class ValidateException extends RuntimeException {
    private final HashMap<String, String> errors;

    public ValidateException(HashMap<String, String> errors) {
        this.errors = errors;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }
}
