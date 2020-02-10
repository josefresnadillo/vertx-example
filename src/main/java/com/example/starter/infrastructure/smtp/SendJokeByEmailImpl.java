package com.example.starter.infrastructure.smtp;

import com.example.starter.domain.SendJokeByEmail;
import com.example.starter.domain.XkcdJoke;
import com.example.starter.infrastructure.smtp.service.EmailService;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class SendJokeByEmailImpl implements SendJokeByEmail {

  private static final Logger LOGGER = LoggerFactory.getLogger(SendJokeByEmailImpl.class.getName());

  private EmailService emailService;

  public SendJokeByEmailImpl(EmailService emailService){
    this.emailService = emailService;
  }

  @Override
  public void send(XkcdJoke joke, String to){
    LOGGER.info("Send joke by email: " + joke.getId());
    emailService.send(joke.toString(), to,
      res2 -> {
        if (res2.succeeded()) {
          LOGGER.info("Send joke " + joke.getId() + " by email: Success!!!!");
        } else {
          LOGGER.info("Send joke " + joke.getId() + " by email: Error: " + res2.cause());
        }
      });
  }
}
