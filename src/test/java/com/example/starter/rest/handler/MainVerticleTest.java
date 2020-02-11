package com.example.starter.rest.handler;

import com.example.starter.rest.BlockingXkcdBbddVerticle;
import com.example.starter.rest.BlockingXkcdSendEmailVerticle;
import com.example.starter.rest.MainVerticle;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * Example of an asynchronous JUnit test for a Verticle.
 */
@ExtendWith(VertxExtension.class)
public class MainVerticleTest {

    Vertx vertx;
    private JsonObject config;
    DeploymentOptions deploymentOptions;

    @BeforeEach
    void prepare() throws IOException {
        HashMap configAsMap =
                new ObjectMapper().readValue("{ \"server\": { \"host\": \"0.0.0.0\", \"port\": 8080, " +
                                "\"context\": \"myservice-api\" }, \"thirdParties\": { \"xkcdJoke\": " +
                                "{ \"url\": \"https://xkcd.com/id/info.0.json\", " +
                                "\"retries\": 3, \"email\": \"jose.fresna@masmovil.com\" } } }",
                        HashMap.class);
        config = JsonObject.mapFrom(configAsMap);
        vertx = Vertx.vertx();
        deploymentOptions = new DeploymentOptions().setConfig(config);
        vertx.deployVerticle(new BlockingXkcdBbddVerticle(), deploymentOptions);
        vertx.deployVerticle(new BlockingXkcdSendEmailVerticle(), deploymentOptions);
    }

    @Test
    public void useSampleVerticle(Vertx vertx, VertxTestContext testContext) throws Exception {
        WebClient webClient = WebClient.create(vertx);
        vertx.deployVerticle(new MainVerticle(), deploymentOptions, testContext.succeeding(id -> {
            webClient.get(8080, "127.0.0.1", "/xkcd")
                    .as(BodyCodec.string())
                    .send(testContext.succeeding(resp -> {
                        testContext.verify(() -> {
                            assertThat(resp.statusCode()).isEqualTo(200);
                            testContext.completeNow();
                        });
                    }));
        }));
    }
}
