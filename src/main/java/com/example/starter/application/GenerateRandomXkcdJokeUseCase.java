package com.example.starter.application;

import com.example.starter.api.XkcdJoke;
import com.example.starter.domain.SendJokeByEmail;
import com.example.starter.domain.XkcdJokeRepository;
import io.reactivex.Single;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateRandomXkcdJokeUseCase {

  private XkcdJokeRepository xkcdJokeRepository;
  private SendJokeByEmail sendJokeByEmail;

  public GenerateRandomXkcdJokeUseCase(XkcdJokeRepository repository, SendJokeByEmail sendJokeByEmail) {
    this.xkcdJokeRepository = repository;
    this.sendJokeByEmail = sendJokeByEmail;
  }

  public Single<XkcdJoke> fetchRandomJoke() {
    Random rand = new Random();
    int jokeId = rand.nextInt(1000);
    Single<com.example.starter.domain.XkcdJoke> joke = xkcdJokeRepository.retrieve(String.valueOf(jokeId));
    joke.subscribe(j -> xkcdJokeRepository.save(j));
    joke.subscribe(j -> sendJokeByEmail.send(j, "jose.fresna@masmovil.es"));
    return joke.map(this::adapt);
  }

  // Not using toMap because the name of some XkcdJoke attributes names are not the same
  // as the fields of the json received from the xkcd service
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
