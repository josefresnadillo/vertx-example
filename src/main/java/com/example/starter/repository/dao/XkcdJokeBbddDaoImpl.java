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

  public XkcdJokeBbddDaoImpl(){}

  @Inject
  public XkcdJokeBbddDaoImpl(Vertx vertx) {
  }

  public void save(String joke, Handler<AsyncResult<String>> resultHandler){
    // TODO
    LOGGER.info("Save joke: " + joke);
    resultHandler.handle(Future.succeededFuture("ok"));
  }
}
