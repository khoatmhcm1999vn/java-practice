package com.mk.examples.converter;

import com.mk.examples.entity.AccessToken;
import com.mk.examples.entity.User;
import com.mk.examples.model.AccessTokenModel;
import com.mk.examples.model.UserModel;

public class EntityToModelConverter {

    public UserModel toModel(User user) {
        if (user == null) {
            return null;
        }
        return new UserModel(
            user.getId(),
            user.getUsername(),
            user.getPassword()
        );
    }

    public AccessTokenModel toModel(AccessToken accessToken) {
        return new AccessTokenModel(
            accessToken.getAccessToken()
        );
    }
}
