package com.example.starter.infrastructure.smtp.service;

import com.example.starter.domain.XkcdDomainException;
import com.example.starter.domain.XkcdJoke;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import javax.inject.Inject;

// A new verticle to send the email

public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class.getName());
    private final ObjectMapper objectMapper;

    public EmailServiceImpl() {
        this.objectMapper = new ObjectMapper();
    }

    @Inject
    public EmailServiceImpl(Vertx vertx) {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void send(String joke, String to, Handler<AsyncResult<String>> resultHandler) {
        //TODO
        final XkcdJoke xkcdJoke = toJoke(joke);
        LOGGER.info("Send joke: " + xkcdJoke.getId() + ": Thread: " + Thread.currentThread().getId());
        resultHandler.handle(Future.succeededFuture(xkcdJoke.getId()));
    }

    private XkcdJoke toJoke(String strJoke) {
        try {
            return objectMapper.readValue(strJoke, XkcdJoke.class);
        } catch (Exception e) {
            LOGGER.debug("Error parsing xkcdJoke! " + e.getMessage());
            throw new XkcdDomainException("Impossible to parse xkcd joke!", e);
        }
    }
}
