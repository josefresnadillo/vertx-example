package com.example.starter.rest.config;

import com.example.starter.rest.handler.XkcdRestHandlerImpl;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {ServiceModule.class})
public interface HandlerComponents {
    XkcdRestHandlerImpl buildXkcdRestHandler();
}
