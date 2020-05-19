package com.example.starter.application;

import com.example.starter.api.XkcdJoke;
import com.example.starter.domain.SendJokeByEmail;
import com.example.starter.domain.XkcdJokeRepository;
import io.reactivex.Single;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateRandomXkcdJokeUseCase {

  private final XkcdJokeRepository xkcdJokeRepository;
  private final SendJokeByEmail sendJokeByEmail;
  private final Random rand = new Random();

  public GenerateRandomXkcdJokeUseCase(XkcdJokeRepository repository, SendJokeByEmail sendJokeByEmail) {
    this.xkcdJokeRepository = repository;
    this.sendJokeByEmail = sendJokeByEmail;
  }

  public Single<XkcdJoke> fetchRandomJoke(String email) {
    int jokeId = rand.nextInt(1000);
    Single<com.example.starter.domain.XkcdJoke> joke = xkcdJokeRepository.retrieve(String.valueOf(jokeId));
    joke.subscribe(xkcdJokeRepository::save).dispose();
    joke.subscribe(j -> sendJokeByEmail.send(j, email)).dispose();
    return joke.map(this::adapt);
  }

  // Not using toMap because the Date attribute from the api XkcdJoke is not
  // the same type as the domain XkcdJoke Date attribute (String vs LocalDateTime)
  private XkcdJoke adapt(com.example.starter.domain.XkcdJoke domain) {
    XkcdJoke api = new XkcdJoke();
    api.setId(domain.getId());
    api.setAlt(domain.getAlt());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    api.setDate(domain.getDate().format(formatter));
    api.setLink(domain.getLink());
    api.setNews(domain.getNews());
    api.setTitle(domain.getTitle());
    api.setSafeTitle(domain.getSafeTitle());
    api.setTranscript(domain.getTranscript());
    api.setUrl(domain.getUrl());
    return api;
  }
}
