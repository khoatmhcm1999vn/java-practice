package com.mk.examples.converter;

import com.mk.examples.model.LoginModel;
import com.mk.examples.request.LoginRequest;

public class RequestToModelConverter {

    public LoginModel toModel(LoginRequest request) {
        return new LoginModel(
            request.getUsername(),
            request.getPassword()
        );
    }
}
