package com.example.starter.repository;

import com.example.starter.domain.XkcdJokeRepository;
import com.example.starter.domain.XkcdJoke;
import com.example.starter.repository.dao.XkcdJokeBbddDao;
import com.example.starter.repository.dao.XkcdJokeRemoteDao;
import io.reactivex.Single;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class XkcdJokeRepositoryImpl implements XkcdJokeRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(XkcdJokeRepositoryImpl.class.getName());

  private final XkcdJokeBbddDao bbddDao;
  private final XkcdJokeRemoteDao remoteDao;

  public XkcdJokeRepositoryImpl(XkcdJokeBbddDao dao, XkcdJokeRemoteDao remoteDao) {
    this.bbddDao = dao;
    this.remoteDao = remoteDao;
  }

  public Single<XkcdJoke> retrieve(String id){
    return remoteDao.retrieve(id);
  }

  public void save(XkcdJoke joke) {
    LOGGER.info("Save Joke: " + joke.getId());
    bbddDao.save(joke.getTranscript(),
      res2 -> {
        if (res2.succeeded()) {
          LOGGER.info("Save Joke " + joke.getId() + " Success!!!!");
        } else {
          LOGGER.info("Save Joke " + joke.getId() + " Error: " + res2.cause());
        }
      });
  }

}
