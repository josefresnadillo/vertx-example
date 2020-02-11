package com.example.starter.rest.service;

import com.example.starter.api.XkcdJoke;
import io.reactivex.Single;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@OpenAPIDefinition(
        info = @Info(
                title = "Xkcd Jokes",
                version = "1.0.0",
                description = "Xkcd Jokes API"
        ),
        externalDocs = @ExternalDocumentation(description = "Repository",
                url = "https://github.com/josefresnadillo/vertx-ddd-template")
)
public interface XkcdRestService {

    @GET
    @Path("/xkcd")
    @Operation(summary = "Get a xkcd joke",
            method = "GET",
            operationId = "/xkcd",
            tags = {
                    "xkcd"
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    encoding = @Encoding(contentType = "application/json"),
                                    schema = @Schema(implementation = XkcdJoke.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(
                                    mediaType = "application/json",
                                    encoding = @Encoding(contentType = "application/json"),
                                    schema = @Schema(implementation = Error.class))
                    )
            })
    Single<XkcdJoke> fetchRandomJoke(String email);
}
