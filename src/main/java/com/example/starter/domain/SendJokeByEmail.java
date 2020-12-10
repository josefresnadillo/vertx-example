package com.example.starter.domain;

public interface SendJokeByEmail {
    void send(XkcdJoke joke, String to);
}
