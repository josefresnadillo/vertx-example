package com.example.starter.rest.handler;

import com.example.starter.api.XkcdJoke;
import com.example.starter.rest.service.XkcdRestService;
import io.reactivex.Single;
import io.vertx.core.json.Json;
import io.vertx.reactivex.core.http.HttpServerResponse;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class XkcdRestHandlerImplTest {

    @Test
    public void test1(){
        HttpServerResponse response = Mockito.mock(HttpServerResponse.class);
        RoutingContext context = Mockito.mock(RoutingContext.class);
        XkcdRestService service = Mockito.mock(XkcdRestService.class);

        XkcdJoke apiJoke = new XkcdJoke();
        apiJoke.setUrl("url");
        apiJoke.setTranscript("transcript");
        apiJoke.setSafeTitle("safetitle");
        apiJoke.setTitle("title");
        apiJoke.setNews("news");
        apiJoke.setLink("link");
        apiJoke.setAlt("alt");
        apiJoke.setDate("01/01/2020");
        apiJoke.setId("id");

        Mockito.when(service.fetchRandomJoke(anyString())).thenReturn(Single.just(apiJoke));
        Mockito.when(context.response()).thenReturn(response);
        Mockito.when(response.setStatusCode(anyInt())).thenReturn(response);
        Mockito.when(response.putHeader(anyString(), anyString())).thenReturn(response);

        XkcdRestHandlerImpl handler = new XkcdRestHandlerImpl(service);
        handler.getContent(context);
        verify(response).end(Json.encodePrettily(apiJoke));
    }

}
