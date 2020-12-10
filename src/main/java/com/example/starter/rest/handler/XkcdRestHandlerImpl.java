package com.example.starter.rest.handler;

import com.example.starter.rest.service.XkcdRestService;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.ext.web.RoutingContext;

import javax.inject.Inject;

public class XkcdRestHandlerImpl {

    private final XkcdRestService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(XkcdRestHandlerImpl.class.getName());

    @Inject
    public XkcdRestHandlerImpl(final XkcdRestService service) {
        this.service = service;
    }

    public void getContent(final RoutingContext context) {
        LOGGER.info("Thread: " + Thread.currentThread().getId());
        service.fetchRandomJoke("").subscribe(response -> {
            LOGGER.info("XkcdRestHandlerImpl OK");
            context.response()
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .setStatusCode(200)
                    .end(Json.encodePrettily(response));
        }, context::fail).dispose();
    }
}
