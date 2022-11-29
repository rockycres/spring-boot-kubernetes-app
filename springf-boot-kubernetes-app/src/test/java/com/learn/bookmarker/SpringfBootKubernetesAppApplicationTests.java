package com.learn.bookmarker;

import com.learn.bookmarker.domain.Bookmark;
import com.learn.bookmarker.domain.BookmarkRespository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo"
})
class SpringBootKubernetesAppApplicationTests {


    @Autowired
    private MockMvc mvc;


    @Autowired
    BookmarkRespository bookmarkRespository;

    private List<Bookmark> bookmarks;

    @BeforeEach
    public void setUp(){
        bookmarkRespository.deleteAllInBatch();
        bookmarks = new ArrayList<>();
        bookmarks.add(
                new Bookmark(null,"testdate1", "http://google.com", Instant.now()));
         bookmarks.add(
                new Bookmark(null,"testdate2", "http://ws.com", Instant.now()));
         bookmarks.add(
                new Bookmark(null,"testdate3", "http://stgoogle.com", Instant.now()));
         bookmarks.add(
                new Bookmark(null,"testdate4", "http://dfdgoogle.com", Instant.now()));
         bookmarks.add(
                new Bookmark(null,"testdate5", "http://hgfhgfgoogle.com", Instant.now()));
         bookmarks.add(
                new Bookmark(null,"testdate6", "http://grrtoogle.com", Instant.now()));
         bookmarks.add(
                new Bookmark(null,"testdate7", "http://gcvvcoogle.com", Instant.now()));
         bookmarks.add(
                new Bookmark(null,"testdate8", "http://gothrthogle.com", Instant.now()));
        bookmarkRespository.saveAll(bookmarks);
    }

    @ParameterizedTest
    @CsvSource({
            "1,8,3,1,true,false,true,false",
            "2,8,3,2,false,false,true,true",
            "3,8,3,3,false,true,false,true"
    })
    void shouldGetBookmarks(int pageNo,int totalNumberOfElements, int totalPages, int currentPage, boolean isFirst,boolean isLast, boolean hasNext, boolean hasPrevious ) throws Exception {
        mvc.perform(get("/api/bookmarks?page="+pageNo))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.totalNumberOfElements", equalTo(totalNumberOfElements)))
            .andExpect(jsonPath("$.totalPages", equalTo(totalPages)))
            .andExpect(jsonPath("$.currentPage", equalTo(currentPage)))
            .andExpect(jsonPath("$.isFirst", equalTo(isFirst)))
            .andExpect(jsonPath("$.isLast", equalTo(isLast)))
            .andExpect(jsonPath("$.hasNext", equalTo(hasNext)))
            .andExpect(jsonPath("$.hasPrevious", equalTo(hasPrevious)));

    }

    @Test
    void shouldCreateBookmarkSuccessfully() throws Exception {
        mvc.perform(post("/api/bookmarks").contentType(MediaType.APPLICATION_JSON).content(
                        """
                        {
                             "title" : "googlecreatetest",                           
                              "url" : "https://googlecreatetets.com"     
                        }
                        """
                ))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title",is("googlecreatetest")))
                .andExpect(jsonPath("$.url", is("https://googlecreatetets.com")));

    }


    @Test
    void shouldFailToCreateBookmarkWhenURLIsNotPresent() throws Exception {
        mvc.perform(post("/api/bookmarks").contentType(MediaType.APPLICATION_JSON).content(
                        """
                        {
                             "title" : "googlecreatetest"                           
                        }
                        """
                ))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", is("application/problem+json")))
                .andExpect(jsonPath("$.type", is("https://zalando.github.io/problem/constraint-violation")))
                .andExpect(jsonPath("$.title", is("Constraint Violation")))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.violations", hasSize(1)))
                .andExpect(jsonPath("$.violations[0].field", is("url")))
                .andExpect(jsonPath("$.violations[0].message", is("URL should not be empty")))
                .andReturn();
    }

}
