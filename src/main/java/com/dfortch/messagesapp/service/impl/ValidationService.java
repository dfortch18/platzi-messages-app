package com.dfortch.messagesapp.service.impl;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public abstract class ValidationService {

    protected static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    protected Validator getValidator() {
        return factory.getValidator();
    }
}
