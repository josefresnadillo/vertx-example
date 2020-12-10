package com.example.starter.rest;

import com.example.starter.rest.config.HandlerComponents;
import com.example.starter.rest.handler.XkcdRestHandlerImpl;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.http.HttpServerResponse;
import io.vertx.reactivex.ext.web.Router;
import com.example.starter.rest.config.DaggerHandlerComponents;
import io.vertx.reactivex.ext.web.handler.StaticHandler;

public class MainVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class.getName());

    @Override
    public void start() {

        final HandlerComponents handlerComponents = DaggerHandlerComponents.create();

        final XkcdRestHandlerImpl handler = handlerComponents.buildXkcdRestHandler();
        final Router router = Router.router(vertx);
        router.route("/xkcd").handler(handler::getContent);

        router.route("/*").handler(StaticHandler.create());

        router.route("/health").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            LOGGER.info("Health!!!");
            response.putHeader("content-type", "text/html")
                    .setStatusCode(200)
                    .end("<h1>Health OK</h1>");
        });

        final String host = config().getJsonObject("server").getString("host");
        final Integer port = config().getJsonObject("server").getInteger("port");

        LOGGER.info("Host: " + host + " port: " + port);

        vertx.createHttpServer().requestHandler(router).rxListen(port, host)
                .doOnError(failure -> {
                    LOGGER.info("Error trying to start server: " + failure.getMessage());
                    System.exit(1);
                })
                .subscribe(httpServer -> {
                    LOGGER.info("HTTP server started on http://" + host + ":" + port);
                }).dispose();
    }
}
