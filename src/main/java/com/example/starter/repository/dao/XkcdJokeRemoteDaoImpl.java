package com.example.starter.repository.dao;

import com.example.starter.domain.XkcdJoke;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.ext.web.client.WebClient;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.Month;

public class XkcdJokeRemoteDaoImpl implements XkcdJokeRemoteDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(XkcdJokeRemoteDaoImpl.class.getName());

    @Getter
    private final String xkcdJokeUrl;
    private final Integer retries;
    private final WebClient webClient;

    public XkcdJokeRemoteDaoImpl(final WebClient webClient,
                                 final String url,
                                 final Integer retries) {
        this.xkcdJokeUrl = url;
        this.retries = retries;
        this.webClient = webClient;
    }

    @Override
    public Single<XkcdJoke> retrieve(final String id) {
        final String url = xkcdJokeUrl.replace("id", String.valueOf(id));
        LOGGER.info("Xkdc joke final url: " + url);
        return webClient.getAbs(url)
                .putHeader("Accept", "*")
                .rxSend().retry(retries).flatMap(response -> {
                    LOGGER.info("Received response with status code : " + response.statusCode() + " Joke Id: " + id);
                    LOGGER.info("Received response: " + response.bodyAsString());
                    return Single.just(adapt(response.bodyAsJsonObject()));
                }).onErrorResumeNext(Single::error);
    }

    // Not using toMap because the Date attribute from the api XkcdJoke is not
    // the same type as the json date attribute (String vs LocalDateTime)
    // Besides some attribute names are different from the json fields
    private XkcdJoke adapt(final JsonObject json) {
        final XkcdJoke xkcdJoke = new XkcdJoke(String.valueOf(json.getInteger("num")));
        xkcdJoke.setDate(LocalDateTime.of(Integer.parseInt(json.getString("year")),
                Month.of(Integer.parseInt(json.getString("month"))),
                Integer.parseInt(json.getString("day")),
                0,
                0));
        xkcdJoke.setNews(safeString(json, "news"));
        xkcdJoke.setSafeTitle(safeString(json, "safe_title"));
        xkcdJoke.setTitle(safeString(json, "title"));
        xkcdJoke.setTranscript(safeString(json, "transcript"));
        xkcdJoke.setAlt(safeString(json, "alt"));
        xkcdJoke.setLink(safeString(json, "link"));
        xkcdJoke.setUrl(safeString(json, "img"));
        return xkcdJoke;
    }

    private String safeString(final JsonObject json,
                              final String key) {
        final String value = json.getString(key);
        return value == null ? "" : value;
    }
}
