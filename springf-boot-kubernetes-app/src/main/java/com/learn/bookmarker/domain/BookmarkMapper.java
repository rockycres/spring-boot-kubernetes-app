package com.learn.bookmarker.domain;

import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {


    public  BookmarkDTO toDTO(Bookmark bookmark){
        BookmarkDTO dto = new BookmarkDTO();
        dto.setId(bookmark.getId());
        dto.setUrl(bookmark.getUrl());
        dto.setTitle(bookmark.getTitle());
        dto.setCreatedAt(bookmark.getCreatedAt());
        return dto;
    }

}
