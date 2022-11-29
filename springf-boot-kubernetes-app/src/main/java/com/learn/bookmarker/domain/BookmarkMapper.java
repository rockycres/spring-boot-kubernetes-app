package com.learn.bookmarker.domain;

import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {


    public  BookmarkDTO toDTO(Bookmark bookmark){
        BookmarkDTO dto = new BookmarkDTO(bookmark.getId(),
        bookmark.getTitle(), bookmark.getUrl(),
        bookmark.getCreatedAt());
        return dto;
    }

}
