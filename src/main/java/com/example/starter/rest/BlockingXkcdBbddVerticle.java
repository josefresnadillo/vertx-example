package com.example.starter.rest;

import com.example.starter.repository.dao.XkcdJokeBbddDao;
import com.example.starter.repository.dao.XkcdJokeBbddDaoImpl;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.serviceproxy.ServiceBinder;

public class BlockingXkcdBbddVerticle extends AbstractVerticle {

    @Override
    public void start() {
        // blocking helloWorld example
        final XkcdJokeBbddDao xkcdJokeBbddDao = new XkcdJokeBbddDaoImpl(vertx.getDelegate());

        // Register the handler
        new ServiceBinder(vertx.getDelegate())
                .setAddress(XkcdJokeBbddDao.SERVICE_ADDRESS)
                .registerLocal(XkcdJokeBbddDao.class, xkcdJokeBbddDao);
    }
}
