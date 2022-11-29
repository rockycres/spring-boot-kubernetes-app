package com.learn.bookmarker.api;

import com.learn.bookmarker.domain.BookmarkDTO;
import com.learn.bookmarker.domain.BookmarksDTO;
import com.learn.bookmarker.domain.BookmarkService;
import com.learn.bookmarker.domain.CreateBookmarkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {


    private final BookmarkService bookmarkService;



    @GetMapping
    public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "query", defaultValue = "") String query){

        if(query == null || query.trim().length() ==0){
            return bookmarkService.listBookmarks(page);
        }

        return bookmarkService.searchBookmarks(query, page);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookmarkDTO createBookmarks(@RequestBody @Valid CreateBookmarkRequest createBookmarkRequest){

        return bookmarkService.createBookmark(createBookmarkRequest);


    }

}
