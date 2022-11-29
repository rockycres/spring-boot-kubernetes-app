package com.learn.bookmarker.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {


    private final BookmarkRespository repository;
    private final BookmarkMapper mapper;


    @Transactional(readOnly = true)
    public BookmarksDTO listBookmarks(Integer page){
        int pageNo = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNo,3,
                Sort.Direction.DESC,"createdAt" );
        Page<BookmarkDTO> bookmarkDTOPage = repository.findBookmarks(pageable);

        return new BookmarksDTO(bookmarkDTOPage);
    }

    @Transactional(readOnly = true)
    public BookmarksDTO searchBookmarks(String query,Integer page) {
        int pageNo = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNo,3,
                Sort.Direction.DESC,"createdAt" );
           Page<BookmarkDTO> bookmarkDTOPage = repository.searchBookMarks(query, pageable);
        Page<BookmarkVM> bookmarkVMPage = repository.findBookmarkByTitleContainsIgnoreCase(query, pageable);

        return new BookmarksDTO(bookmarkDTOPage);

    }


    public BookmarkDTO createBookmark(CreateBookmarkRequest createBookmarkRequest) {
        Bookmark bookmark = new Bookmark(null, createBookmarkRequest.getTitle(), createBookmarkRequest.getUrl(), Instant.now());
        Bookmark savedBookmark  =  repository.save(bookmark);
        return mapper.toDTO(savedBookmark);
    }
}
