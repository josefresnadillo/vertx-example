package com.example.starter.repository.dao;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

// A new verticle to access the ddbb

@ProxyGen
public interface XkcdJokeBbddDao {

    String SERVICE_ADDRESS = "database-service-address";

    // A couple of factory methods to create an instance and a proxy
    static XkcdJokeBbddDao create(final Vertx vertx) {
        return new XkcdJokeBbddDaoImpl(vertx);
    }

    static XkcdJokeBbddDao createProxy(final Vertx vertx,
                                       final String address) {
        return new XkcdJokeBbddDaoVertxEBProxy(vertx, address);
    }

    void save(final String joke,
              final Handler<AsyncResult<String>> resultHandler);
}
