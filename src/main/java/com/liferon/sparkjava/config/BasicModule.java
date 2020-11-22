package com.liferon.sparkjava.config;

import com.google.inject.AbstractModule;
import com.liferon.sparkjava.service.UserService;
import com.liferon.sparkjava.service.UserServiceMapImpl;

public class BasicModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserService.class).to(UserServiceMapImpl.class);
    }
}
