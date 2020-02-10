package com.example.starter.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

// Entity

@Getter
@ToString
@EqualsAndHashCode
public class XkcdJoke {

  private String id;

  @Setter
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

  public XkcdJoke(String id) {
    super();
    this.id = id;
  }

}
