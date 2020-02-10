package com.example.starter.domain;

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
