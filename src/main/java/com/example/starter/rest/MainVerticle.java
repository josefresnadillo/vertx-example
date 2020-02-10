package com.example.starter.rest;

import com.example.starter.rest.handler.XkcdRestHandlerImpl;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;
import com.example.starter.rest.config.DaggerHandlerComponents;

public class MainVerticle extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class.getName());

  @Override
  public void start(Future<Void> fut) {

    var handlerComponents = DaggerHandlerComponents.create();

    XkcdRestHandlerImpl handler = handlerComponents.buildXkcdRestHandler();
    Router router = Router.router(vertx);
    router.route("/xkcd").handler(handler::getContent);

    vertx.createHttpServer().requestHandler(router).rxListen(8080, "0.0.0.0")
      .doOnError(failure -> {
        LOGGER.info("Error trying to start server: " + failure.getMessage());
        System.exit(1);
      })
      .subscribe(httpServer -> {
        LOGGER.info("HTTP server started on http://0.0.0.0:8080");
      });
  }
}
