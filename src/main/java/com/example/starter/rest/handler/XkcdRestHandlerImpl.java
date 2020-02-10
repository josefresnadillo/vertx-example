package com.example.starter.rest.handler;

import com.example.starter.application.FetchRandomXkcdJoke;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.ext.web.RoutingContext;

import javax.inject.Inject;

public class XkcdRestHandlerImpl {

  private final FetchRandomXkcdJoke service;

  private static final Logger LOGGER = LoggerFactory.getLogger(XkcdRestHandlerImpl.class.getName());

  @Inject
  public XkcdRestHandlerImpl(FetchRandomXkcdJoke service) {
    this.service = service;
  }

  public void getContent(RoutingContext context) {
    LOGGER.info("Route: " + context.normalisedPath());
    service.fetchRandomJoke().subscribe(response -> {
      LOGGER.info("XkcdRestHandlerImpl OK");
      context.response()
        .putHeader("content-type", "application/json; charset=utf-8")
        .setStatusCode(200)
        .end(Json.encodePrettily(response));
    }, context::fail);
  }
}
