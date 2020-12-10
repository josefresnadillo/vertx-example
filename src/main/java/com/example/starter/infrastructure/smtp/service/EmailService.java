package com.example.starter.infrastructure.smtp.service;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

@ProxyGen
public interface EmailService {

    String SERVICE_ADDRESS = "email-service-address";

    // A couple of factory methods to create an instance and a proxy
    static EmailService create(final Vertx vertx) {
        return new EmailServiceImpl(vertx);
    }

    static EmailService createProxy(final Vertx vertx,
                                    final String address) {
        return new EmailServiceVertxEBProxy(vertx, address);
    }

    void send(final String joke,
              final String to,
              final Handler<AsyncResult<String>> resultHandler);
}
