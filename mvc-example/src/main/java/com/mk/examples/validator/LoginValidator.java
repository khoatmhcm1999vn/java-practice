package com.mk.examples.validator;

import com.mk.examples.exception.BadRequestException;
import com.mk.examples.request.LoginRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginValidator {

    public void validate(LoginRequest request) {
        Map<String, String> errors = new HashMap<>();
        if (request.getUsername() == null) {
            errors.put("username", "required");
        }
        if (request.getPassword() == null) {
            errors.put("password", "required");
        }
        if (errors.size() > 0) {
            throw new BadRequestException(errors);
        }
    }
}
