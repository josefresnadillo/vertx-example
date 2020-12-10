package com.example.starter.rest.service;

import com.example.starter.api.XkcdJoke;
import com.example.starter.application.GenerateRandomXkcdJokeUseCase;
import io.reactivex.Single;

public class XkcdRestServiceImpl implements XkcdRestService {

    private final GenerateRandomXkcdJokeUseCase generateRandomXkcdJokeUseCase;

    public XkcdRestServiceImpl(GenerateRandomXkcdJokeUseCase generateRandomXkcdJokeUseCase) {
        this.generateRandomXkcdJokeUseCase = generateRandomXkcdJokeUseCase;
    }

    @Override
    public Single<XkcdJoke> fetchRandomJoke(String email) {
        return generateRandomXkcdJokeUseCase.fetchRandomJoke(email);
    }
}
