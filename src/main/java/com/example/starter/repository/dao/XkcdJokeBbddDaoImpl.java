package com.example.starter.repository.dao;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import javax.inject.Inject;

public class XkcdJokeBbddDaoImpl implements XkcdJokeBbddDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(XkcdJokeBbddDaoImpl.class.getName());
    private static final int MAXJOKELOG = 100;

    public XkcdJokeBbddDaoImpl() {
    }

    @Inject
    public XkcdJokeBbddDaoImpl(Vertx vertx) {
    }

    public void save(String joke, Handler<AsyncResult<String>> resultHandler) {
        // TODO
        LOGGER.info("Saving joke: " + safeSubString(joke) + ": Thread: " + Thread.currentThread().getId());
        resultHandler.handle(Future.succeededFuture("ok"));
    }

    private String safeSubString(String joke) {
        String result = joke;
        result = result.replace("\\n", " ");
        if (joke.length() > MAXJOKELOG) {
            result = result.substring(0, MAXJOKELOG) + "...";
        }
        return result;
    }
}
