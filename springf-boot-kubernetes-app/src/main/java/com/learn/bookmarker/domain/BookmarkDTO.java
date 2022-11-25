package com.learn.bookmarker.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="bookmarks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkDTO {

    @Id
    @SequenceGenerator(name = "bo_id_seq_gen", sequenceName = "bo_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bo_id_seq_gen")
    private Long id;

    @Column(nullable = false)
    private  String title;

    @Column(nullable = false)
    private String url;

    private Instant createdAt;



}
