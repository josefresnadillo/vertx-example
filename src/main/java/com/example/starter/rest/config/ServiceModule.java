package com.example.starter.rest.config;

import com.example.starter.application.GenerateRandomXkcdJokeUseCase;
import com.example.starter.domain.SendJokeByEmail;
import com.example.starter.infrastructure.smtp.SendJokeByEmailImpl;
import com.example.starter.infrastructure.smtp.service.EmailService;
import com.example.starter.repository.dao.XkcdJokeBbddDao;
import com.example.starter.repository.dao.XkcdJokeRemoteDao;
import com.example.starter.domain.XkcdJokeRepository;
import com.example.starter.repository.dao.XkcdJokeRemoteDaoImpl;
import com.example.starter.repository.XkcdJokeRepositoryImpl;
import com.example.starter.rest.service.XkcdRestService;
import com.example.starter.rest.service.XkcdRestServiceImpl;
import dagger.Provides;
import dagger.Module;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.serviceproxy.ServiceProxyBuilder;

import javax.inject.Singleton;

@Module
public class ServiceModule {

    // Rest layer

    @Provides
    @Singleton
    public XkcdRestService restService(final GenerateRandomXkcdJokeUseCase generateRandomXkcdJokeUseCase) {
        return new XkcdRestServiceImpl(generateRandomXkcdJokeUseCase);
    }

    // Application layer

    @Provides
    @Singleton
    public GenerateRandomXkcdJokeUseCase fetchJoke(final XkcdJokeRepository repository,
                                                   final SendJokeByEmail sendJokeByEmail) {
        return new GenerateRandomXkcdJokeUseCase(repository, sendJokeByEmail);
    }


    // Domain layer

    @Provides
    @Singleton
    public SendJokeByEmail sendJokeByEmail(final EmailService emailService) {
        return new SendJokeByEmailImpl(emailService);
    }

    @Provides
    @Singleton
    public XkcdJokeRepository retrieveJoke(final XkcdJokeBbddDao xkcdBBDDDao,
                                           final XkcdJokeRemoteDao xkcdRemoteDao) {
        return new XkcdJokeRepositoryImpl(xkcdBBDDDao, xkcdRemoteDao);
    }

    // Repository layer

    @Provides
    @Singleton
    public XkcdJokeRemoteDao xkcdJokeRemoteDao() {
        final String url = Vertx.currentContext().config()
                .getJsonObject("thirdParties")
                .getJsonObject("xkcdJoke")
                .getString("url");
        final Integer retries = Vertx.currentContext().config()
                .getJsonObject("thirdParties")
                .getJsonObject("xkcdJoke")
                .getInteger("retries");
        return new XkcdJokeRemoteDaoImpl(WebClient.create(Vertx.currentContext().owner()), url, retries);
    }

    // A new verticle to access the ddbb
    @Provides
    @Singleton
    public XkcdJokeBbddDao xkcdJokeBbddDao() {
        ServiceProxyBuilder builder = new ServiceProxyBuilder(Vertx.currentContext().owner().getDelegate())
                .setAddress(XkcdJokeBbddDao.SERVICE_ADDRESS);
        return builder.build(XkcdJokeBbddDao.class);
    }

    // Email infrastructure layer

    // A new verticle to send the email
    @Provides
    @Singleton
    public EmailService emailService() {
        final ServiceProxyBuilder builder = new ServiceProxyBuilder(Vertx.currentContext().owner().getDelegate())
                .setAddress(EmailService.SERVICE_ADDRESS);
        return builder.build(EmailService.class);
    }
}
