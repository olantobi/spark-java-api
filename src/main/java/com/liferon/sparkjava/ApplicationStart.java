package com.liferon.sparkjava;

import com.liferon.sparkjava.controller.UserController;

public class ApplicationStart {
    public static void main(String[] args) {
        UserController userController = new UserController();
        userController.routes();
    }
}
