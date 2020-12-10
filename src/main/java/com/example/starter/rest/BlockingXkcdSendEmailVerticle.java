package com.example.starter.rest;

import com.example.starter.infrastructure.smtp.service.EmailService;
import com.example.starter.infrastructure.smtp.service.EmailServiceImpl;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.serviceproxy.ServiceBinder;

public class BlockingXkcdSendEmailVerticle extends AbstractVerticle {

    @Override
    public void start() {
        // blocking helloWorld example
        EmailService emailService = new EmailServiceImpl(vertx.getDelegate());

        // Register the handler
        new ServiceBinder(vertx.getDelegate())
                .setAddress(EmailService.SERVICE_ADDRESS)
                .registerLocal(EmailService.class, emailService);
    }
}
