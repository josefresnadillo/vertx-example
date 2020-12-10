package com.example.starter.domain;

public interface SendJokeByEmail {
    void send(final XkcdJoke joke, final String to);
}
