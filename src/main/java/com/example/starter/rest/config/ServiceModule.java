package com.example.starter.rest.config;

import com.example.starter.application.GenerateRandomXkcdJokeUseCase;
import com.example.starter.domain.SendJokeByEmail;
import com.example.starter.infrastructure.smtp.SendJokeByEmailImpl;
import com.example.starter.infrastructure.smtp.service.EmailService;
import com.example.starter.infrastructure.smtp.service.EmailServiceImpl;
import com.example.starter.repository.dao.XkcdJokeBbddDao;
import com.example.starter.repository.dao.XkcdJokeBbddDaoImpl;
import com.example.starter.repository.dao.XkcdJokeRemoteDao;
import com.example.starter.domain.XkcdJokeRepository;
import com.example.starter.repository.dao.XkcdJokeRemoteDaoImpl;
import com.example.starter.repository.XkcdJokeRepositoryImpl;
import dagger.Provides;
import dagger.Module;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.serviceproxy.ServiceBinder;
import javax.inject.Singleton;

@Module
public class ServiceModule {

  @Provides
  @Singleton
  public WebClient provideWebClient() {
    return WebClient.create(Vertx.currentContext().owner());
  }

  @Provides
  @Singleton
  public GenerateRandomXkcdJokeUseCase fetchJoke(XkcdJokeRepository repository, SendJokeByEmail sendJokeByEmail) {
    return new GenerateRandomXkcdJokeUseCase(repository, sendJokeByEmail);
  }

  @Provides
  @Singleton
  public SendJokeByEmail sendJokeByEmail(EmailService emailService) {
    return new SendJokeByEmailImpl(emailService);
  }

  @Provides
  @Singleton
  public XkcdJokeRepository retrieveJoke(XkcdJokeBbddDao xkcdBBDDDao, XkcdJokeRemoteDao xkcdRemoteDao) {
    return new XkcdJokeRepositoryImpl(xkcdBBDDDao, xkcdRemoteDao);
  }

  @Provides
  @Singleton
  public XkcdJokeRemoteDao xkcdJokeRemoteDao(WebClient webClient) {
    String url = Vertx.currentContext().config().getJsonObject("thirdParties").getJsonObject("xkcdJoke").getString("url");
    return new XkcdJokeRemoteDaoImpl(webClient, url);
  }

  @Provides
  @Singleton
  public XkcdJokeBbddDao xkcdJokeBbddDao(){
    XkcdJokeBbddDao dao = new XkcdJokeBbddDaoImpl();
    new ServiceBinder(Vertx.currentContext().owner().getDelegate()).setAddress("database-service-address").register(XkcdJokeBbddDao.class, dao);
    return XkcdJokeBbddDao.createProxy(Vertx.currentContext().owner().getDelegate(), "database-service-address");
  }

  @Provides
  @Singleton
  public EmailService emailService(){
    EmailService emailService = new EmailServiceImpl();
    new ServiceBinder(Vertx.currentContext().owner().getDelegate()).setAddress("email-service-address").register(EmailService.class, emailService);
    return EmailService.createProxy(Vertx.currentContext().owner().getDelegate(), "email-service-address");
  }
}
