/*
package com.learn.bookmarker;

import com.learn.bookmarker.domain.Bookmark;
import com.learn.bookmarker.domain.BookmarkRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.Instant;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner{

    private final BookmarkRespository repository;


    @Override
    public void run(String... args) throws Exception {
        repository.save(
                new Bookmark(null,"testdate1", "http://google.com", Instant.now()));
        repository.save(
                new Bookmark(null,"testdate2", "http://ws.com", Instant.now()));
        repository.save(
                new Bookmark(null,"testdate3", "http://stgoogle.com", Instant.now()));
        repository.save(
                new Bookmark(null,"testdate4", "http://dfdgoogle.com", Instant.now()));
        repository.save(
                new Bookmark(null,"testdate5", "http://hgfhgfgoogle.com", Instant.now()));
        repository.save(
                new Bookmark(null,"testdate6", "http://grrtoogle.com", Instant.now()));
        repository.save(
                new Bookmark(null,"testdate7", "http://gcvvcoogle.com", Instant.now()));
        repository.save(
                new Bookmark(null,"testdate8", "http://gothrthogle.com", Instant.now()));

    }
}
*/
