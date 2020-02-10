package com.example.starter.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class XkcdJoke {
    private String id;
    private String date;
    private String link;
    private String news;
    private String title;
    private String safeTitle;
    private String transcript;
    private String alt;
    private String url;
}
