package com.example.starter.infrastructure.smtp.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import javax.inject.Inject;

public class EmailServiceImpl implements EmailService {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class.getName());

  public EmailServiceImpl(){ }

  @Inject
  public EmailServiceImpl(Vertx vertx) {}

  @Override
  public void send(String joke, String to, Handler<AsyncResult<String>> resultHandler){
    LOGGER.info("Send joke: " + joke);
    resultHandler.handle(Future.succeededFuture("result"));
  }
}
