package com.example.starter.infrastructure.smtp.service;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

@ProxyGen
public interface EmailService {

  // A couple of factory methods to create an instance and a proxy
  static EmailService create(Vertx vertx) {
    return new EmailServiceImpl(vertx);
  }

  static EmailService createProxy(Vertx vertx, String address) {
    return new EmailServiceVertxEBProxy(vertx, address);
  }

  void send(String joke, String to, Handler<AsyncResult<String>> resultHandler);
}
