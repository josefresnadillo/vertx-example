package com.example.starter.domain;

import io.reactivex.Single;

public interface XkcdJokeRepository {
    Single<XkcdJoke> retrieve(String id);

    void save(XkcdJoke joke);
}
