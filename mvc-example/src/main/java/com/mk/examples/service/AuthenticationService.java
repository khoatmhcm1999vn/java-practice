package com.mk.examples.service;

import com.mk.examples.exception.AuthenticationException;
import com.mk.examples.manager.BeanManager;
import com.mk.examples.model.AccessTokenModel;
import com.mk.examples.model.LoginModel;
import com.mk.examples.model.UserModel;

public class AuthenticationService {

    private final UserService userService = BeanManager.getInstance()
        .getBean(UserService.class);

    public AccessTokenModel authenticate(LoginModel model) {
        UserModel userModel = userService.getUserByUsername(model.getUsername());
        if (userModel == null || !model.getPassword().equals(userModel.getPassword())) {
            throw new AuthenticationException();
        }
        return userService.getAccessTokenByUserId(
            userModel.getId()
        );
    }
}
