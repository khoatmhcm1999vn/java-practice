package com.mk.examples;

import com.mk.examples.controller.LoginController;
import com.mk.examples.converter.EntityToModelConverter;
import com.mk.examples.converter.RequestToModelConverter;
import com.mk.examples.manager.BeanManager;
import com.mk.examples.repository.AccessTokenRepository;
import com.mk.examples.repository.UserRepository;
import com.mk.examples.request.LoginRequest;
import com.mk.examples.response.LoginResponse;
import com.mk.examples.service.AuthenticationService;
import com.mk.examples.service.UserService;
import com.mk.examples.validator.LoginValidator;

public class Application {

    public static void main(String[] args) {
        initializeBeans();
        testLogin();
    }

    private static void testLogin() {
        LoginRequest request = new LoginRequest("admin", "123456");
        LoginController controller = BeanManager.getInstance().getBean(LoginController.class);
        LoginResponse response = controller.loginPost(request);
        System.out.println(response);
    }

    private static void initializeBeans() {
        BeanManager beanManager = BeanManager.getInstance();
        beanManager.addBean(AccessTokenRepository.class, new AccessTokenRepository());
        beanManager.addBean(UserRepository.class, new UserRepository());
        beanManager.addBean(EntityToModelConverter.class, new EntityToModelConverter());
        beanManager.addBean(RequestToModelConverter.class, new RequestToModelConverter());
        beanManager.addBean(UserService.class, new UserService());
        beanManager.addBean(AuthenticationService.class, new AuthenticationService());
        beanManager.addBean(LoginValidator.class, new LoginValidator());
        beanManager.addBean(LoginController.class, new LoginController());
    }
}