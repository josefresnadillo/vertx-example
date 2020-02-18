package com.example.starter;

import com.example.starter.rest.BlockingXkcdBbddVerticle;
import com.example.starter.rest.BlockingXkcdSendEmailVerticle;
import com.example.starter.rest.MainVerticle;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.config.ConfigRetriever;
import io.vertx.reactivex.core.Vertx;

import java.util.concurrent.TimeUnit;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        ConfigStoreOptions store = new ConfigStoreOptions().setType("file").setFormat("yaml").setConfig(new JsonObject().put("path", "vxcnf/config.yaml"));

        final String vertxConfigPath = System.getenv("VERTX_CONFIG_PATH");
        if (vertxConfigPath != null) {
            LOGGER.info("Reading configuration from " + vertxConfigPath + "-");
            store.setConfig(new JsonObject().put("path", vertxConfigPath));
        }

        LOGGER.info("Reading configuration...");
        ConfigRetriever retriever = ConfigRetriever.create(vertx, new ConfigRetrieverOptions().addStore(store));
        retriever.getConfig(ar -> {

            LOGGER.info("Reading configuration... done!");

            DeploymentOptions deploymentOptions = new DeploymentOptions().setConfig(ar.result()).setInstances(3);
            vertx.deployVerticle(MainVerticle.class.getName(), deploymentOptions);

            DeploymentOptions workerDeploymentOptions = new DeploymentOptions().setWorker(true)
                    .setInstances(3) // matches the worker pool size below
                    .setWorkerPoolName("helloWorld-worker-pool")
                    .setWorkerPoolSize(3)
                    .setMaxWorkerExecuteTime(10)
                    .setMaxWorkerExecuteTimeUnit(TimeUnit.SECONDS).setConfig(ar.result());

            vertx.deployVerticle(BlockingXkcdBbddVerticle.class.getName(), workerDeploymentOptions);
            vertx.deployVerticle(BlockingXkcdSendEmailVerticle.class.getName(), workerDeploymentOptions);

        });
    }
}
