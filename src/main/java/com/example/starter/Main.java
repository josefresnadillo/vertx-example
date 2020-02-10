package com.example.starter;

import com.example.starter.rest.MainVerticle;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.config.ConfigRetriever;
import io.vertx.reactivex.core.Vertx;

public class Main {

  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class.getName());

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    ConfigStoreOptions store = new ConfigStoreOptions()
      .setType("file")
      .setFormat("yaml")
      .setConfig(new JsonObject().put("path", "config.yaml")
      );

    LOGGER.info("Reading configuration...");
    ConfigRetriever retriever = ConfigRetriever.create(vertx, new ConfigRetrieverOptions().addStore(store));
    retriever.getConfig(ar -> {
      LOGGER.info("Reading configuration... done!");
      DeploymentOptions deploymentOptions = new DeploymentOptions().setConfig(ar.result());
      vertx.deployVerticle(new MainVerticle(), deploymentOptions);
    });
  }
}
