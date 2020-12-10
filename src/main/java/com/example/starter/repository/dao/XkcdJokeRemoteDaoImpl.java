package com.example.starter.repository.dao;

import com.example.starter.domain.XkcdJoke;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.ext.web.client.WebClient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.Month;


public class XkcdJokeRemoteDaoImpl implements XkcdJokeRemoteDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(XkcdJokeRemoteDaoImpl.class.getName());

    @Getter
    @Setter
    private String xkcdJokeUrl;
    private Integer retries;
    private final WebClient webClient;

    public XkcdJokeRemoteDaoImpl(WebClient webClient, String url, Integer retries) {
        this.xkcdJokeUrl = url;
        this.retries = retries;
        this.webClient = webClient;
    }

    @Override
    public Single<XkcdJoke> retrieve(String id) {
        String url = xkcdJokeUrl.replace("id", String.valueOf(id));
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
    private XkcdJoke adapt(JsonObject json) {
        XkcdJoke xkcdJoke = new XkcdJoke(String.valueOf(json.getInteger("num")));
        String day = json.getString("day");
        String month = json.getString("month");
        String year = json.getString("year");
        xkcdJoke.setDate(LocalDateTime.of(Integer.parseInt(year), Month.of(Integer.parseInt(month)), Integer.parseInt(day), 0, 0));
        xkcdJoke.setNews(safeString(json, "news"));
        xkcdJoke.setSafeTitle(safeString(json, "safe_title"));
        xkcdJoke.setTitle(safeString(json, "title"));
        xkcdJoke.setTranscript(safeString(json, "transcript"));
        xkcdJoke.setAlt(safeString(json, "alt"));
        xkcdJoke.setLink(safeString(json, "link"));
        xkcdJoke.setUrl(safeString(json, "img"));
        return xkcdJoke;
    }

    private String safeString(JsonObject json, String key) {
        String value = json.getString(key);
        return value == null ? "" : value;
    }
}
