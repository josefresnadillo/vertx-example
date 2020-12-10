package com.example.starter.domain;

import io.reactivex.Single;

public interface XkcdJokeRepository {
    Single<XkcdJoke> retrieve(final String id);

    void save(final XkcdJoke joke);
}
