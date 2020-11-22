package com.liferon.sparkjava;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.liferon.sparkjava.config.BasicModule;
import com.liferon.sparkjava.controller.UserController;
import com.liferon.sparkjava.service.UserService;

public class ApplicationStart {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BasicModule());
        UserService userService = injector.getInstance(UserService.class);

        UserController userController = new UserController(userService);
        userController.routes();
    }
}
