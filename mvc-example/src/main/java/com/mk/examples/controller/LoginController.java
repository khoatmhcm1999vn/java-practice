package com.mk.examples.controller;

import com.mk.examples.converter.RequestToModelConverter;
import com.mk.examples.manager.BeanManager;
import com.mk.examples.model.AccessTokenModel;
import com.mk.examples.request.LoginRequest;
import com.mk.examples.response.LoginResponse;
import com.mk.examples.service.AuthenticationService;
import com.mk.examples.validator.LoginValidator;

public class LoginController {

    private final AuthenticationService authenticationService = BeanManager.getInstance()
        .getBean(AuthenticationService.class);
    private final LoginValidator loginValidator = BeanManager.getInstance()
        .getBean(LoginValidator.class);
    private final RequestToModelConverter requestToModelConverter = BeanManager.getInstance()
        .getBean(RequestToModelConverter.class);

    // @DoPost("/login")
    public LoginResponse loginPost(LoginRequest request) {
        loginValidator.validate(request);
        AccessTokenModel accessTokenModel = authenticationService.authenticate(
            requestToModelConverter.toModel(request)
        );
        return new LoginResponse(accessTokenModel.getAccessToken());
    }
}
