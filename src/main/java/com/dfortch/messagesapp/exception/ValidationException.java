package com.dfortch.messagesapp.exception;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class ValidationException extends RuntimeException {

    private final Set<? extends ConstraintViolation<?>> violations;

    public ValidationException(Set<? extends ConstraintViolation<?>> violations) {
        super("Validation failed");
        this.violations = violations;
    }

    public Set<? extends ConstraintViolation<?>> getViolations() {
        return violations;
    }
}
