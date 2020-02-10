package com.example.starter.repository.dao;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import javax.inject.Inject;

public class XkcdJokeBbddDaoImpl implements XkcdJokeBbddDao {

  public XkcdJokeBbddDaoImpl(){}

  @Inject
  public XkcdJokeBbddDaoImpl(Vertx vertx) {
  }

  public void save(String joke, Handler<AsyncResult<String>> resultHandler){
    resultHandler.handle(Future.succeededFuture("ok"));
  }
}
