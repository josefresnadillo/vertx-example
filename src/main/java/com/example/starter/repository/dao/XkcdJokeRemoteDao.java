package com.example.starter.repository.dao;

import com.example.starter.domain.XkcdJoke;
import io.reactivex.Single;

public interface XkcdJokeRemoteDao {
  Single<XkcdJoke> retrieve(String id);
}
