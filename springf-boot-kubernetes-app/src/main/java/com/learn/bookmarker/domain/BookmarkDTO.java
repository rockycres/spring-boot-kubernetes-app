package com.learn.bookmarker.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;



@Setter
@Getter
@AllArgsConstructor
public class BookmarkDTO {

    private Long id;

    private  String title;

    private String url;

    private Instant createdAt;



}
