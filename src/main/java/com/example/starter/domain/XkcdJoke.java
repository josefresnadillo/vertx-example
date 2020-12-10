package com.example.starter.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;

// Entity

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class XkcdJoke {

    private String id;

    @Setter
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;
    @Setter
    private String link;
    @Setter
    private String news;
    @Setter
    private String title;
    @Setter
    private String safeTitle;
    @Setter
    private String transcript;
    @Setter
    private String alt;
    @Setter
    private String url;

    public XkcdJoke(final String id) {
        super();
        this.id = id;
    }

}
